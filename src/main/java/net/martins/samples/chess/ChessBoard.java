package net.martins.samples.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {
	
	private int width;
	private int height;
	private ChessPiece[] pieces;

	public ChessBoard(int width, int height, ChessPiece[] pieces) {

		this.width = width;
		this.height = height;
		this.pieces = pieces;
	}

	/**
	 * Find all unique configurations of the set of chess pieces on this chess board
	 * @return
	 */
	public Results searchLayouts() {

		Results results = new Results();
		
		ChessLayout chessLayout = new ChessLayout(width, height);

		placePieceOnBoard(results, chessLayout, new ArrayList(Arrays.asList(pieces)), 0);

		return results;
	}
	
	/**
	 * Tries to place a piece on all positions of the chess board
	 * @param results where to store all completed board layouts
	 * @param chessLayout Chess board layout (may contain previously placed chess pieces)
	 * @param piecesToPlace List of all chess pieces to place on board
	 * @param pieceIndex index of the list of pieces of the chess piece to place
	 */
	private void placePieceOnBoard(Results results, ChessLayout chessLayout, List<ChessPiece> piecesToPlace, int pieceIndex) {
		if(pieceIndex == piecesToPlace.size()) {
			// no more chess pieces to place. store the completed layout
			results.addLayout(chessLayout);
		}
		else {
			
			ChessPiece chessPiece = piecesToPlace.get(pieceIndex);
			int offset = 0;
			while(offset < chessLayout.getBoardLength()) {
				
				int placedOffset = chessLayout.placePieceInNextAvailablePosition(chessPiece, offset);
				if( placedOffset == ChessLayout.NULL_OFFSET ) 
					break;
				else {
					int nextIndex = pieceIndex + 1;
					placePieceOnBoard(results, chessLayout.clone(), piecesToPlace, nextIndex);
					
					// if piece at index + 1 equals piece at index, then no need to try other positions with piece at index
					if(nextIndex < piecesToPlace.size() && chessPiece.equals(piecesToPlace.get(nextIndex)))
						break;
					
					// backtrack and try next offset
					chessLayout.removeChessPiece(chessPiece);
					offset = placedOffset + 1;
				}
			}
		}

	}
	
}
