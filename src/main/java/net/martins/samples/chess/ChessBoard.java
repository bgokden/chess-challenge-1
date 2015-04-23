package net.martins.samples.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

		// Sort the array of pieces do guarantee that repeated pieces are together
		Arrays.sort(pieces, new Comparator<ChessPiece>() {

			public int compare(ChessPiece o1, ChessPiece o2) {
				if(o1.getSymbol() == o2.getSymbol())
					return 0;
				else
					if(o1.getSymbol() > o2.getSymbol())
						return 1;
					else
						return -1;
			}
			
		});
		
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
	
	/**
	 * Find permutations of a list of chess pieces using a recurrent method.<p>
	 * Based on the algorithm by Eric Leschinski (http://www.ericleschinski.com/c/java_permutations_recursion/)
	 * @param permutations
	 * @param collect
	 * @param distrib
	 */
	private void buildChessPiecePermutations(List<List<ChessPiece>> permutations, List<ChessPiece> collect, List<ChessPiece> distrib) {
		int n = distrib.size();
		if(n == 0)
			permutations.add(new ArrayList<ChessPiece>(collect));
		else {
			for(int i = 0; i < n; i++) {
				List<ChessPiece> nestedCollect = new ArrayList<ChessPiece>(collect);
				List<ChessPiece> nestedDistrib = new ArrayList<ChessPiece>(distrib);
				nestedCollect.add(nestedDistrib.remove(i));
				buildChessPiecePermutations(permutations, nestedCollect, nestedDistrib);
			}
		}
	}

}
