package net.martins.samples.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class ChessBoard {
	
	private static final Logger logger = Logger.getLogger(ChessBoard.class);
	
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
	public Map<Integer, ChessLayout> searchLayouts() {

		Map<Integer, ChessLayout> foundLayouts = new HashMap<Integer, ChessLayout>();
		
		ChessLayout chessLayout = new ChessLayout(width, height);

		placePieceOnBoard(foundLayouts, chessLayout, new ArrayList(Arrays.asList(pieces)), 0);

		return foundLayouts;
	}
	
	/**
	 * Tries to place a piece on all positions of the chess board
	 * @param completedLayouts where to store all completed board layouts
	 * @param chessLayout Chess board layout (may contain previously placed chess pieces)
	 * @param piecesToPlace List of all chess pieces to place on board
	 * @param pieceIndex index of the list of pieces of the chess piece to place
	 */
	private void placePieceOnBoard(Map<Integer, ChessLayout> completedLayouts, ChessLayout chessLayout, List<ChessPiece> piecesToPlace, int pieceIndex) {
		if(pieceIndex == piecesToPlace.size()) {
			// no more chess pieces to place. store the completed layout
			Integer hash = Integer.valueOf(chessLayout.hashCode());
			ChessLayout previous = completedLayouts.put(hash, chessLayout);
			if(previous != null && logger.isDebugEnabled()) {
				logger.debug("Layout # " + completedLayouts.size() + " is a duplicate:");
				logger.debug("\n" + chessLayout.getLayoutText());
			}
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
					placePieceOnBoard(completedLayouts, chessLayout.clone(), piecesToPlace, nextIndex);
					
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
