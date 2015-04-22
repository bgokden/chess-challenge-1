package net.martins.samples.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public List<ChessLayout> searchLayouts() {

		List<ChessLayout> foundLayouts = new ArrayList<ChessLayout>();
		
		// get a list of all the possible permutations based on the chess pieces available
		
		List<List<ChessPiece>> permutations = new ArrayList<List<ChessPiece>>();
		
		buildChessPiecePermutations(permutations, new ArrayList<ChessPiece>(), new ArrayList(Arrays.asList(pieces)));
		
		// for each permutation try and find chess board layouts starting from every position on the board
		
		int boardLength = width * height;
		
//		for(List<ChessPiece> permutation : permutations) {
		
		List<ChessPiece> permutation = permutations.get(0);
			
				ChessLayout chessLayout = new ChessLayout(width, height);

				// if is able to complete the layout, then store it.
				completeLayout(foundLayouts, chessLayout, permutation, 0);
//		}

		return foundLayouts;
	}
	
	/**
	 * Tries to place all the pieces in a layout
	 * @param chessLayout
	 * @param piecesToPlace
	 * @return
	 */
	private boolean completeLayout(List<ChessLayout> completedLayouts, ChessLayout chessLayout, List<ChessPiece> piecesToPlace, int pieceIndex) {
		if(pieceIndex == piecesToPlace.size()) {
			completedLayouts.add(chessLayout);
			if(logger.isDebugEnabled()) {
				logger.debug("Completed layouts: " + completedLayouts.size());
				logger.debug("\n" + chessLayout.getLayoutText());
			}
			return true; // no more chess pieces to place
		}

		ChessPiece chessPiece = piecesToPlace.get(pieceIndex);
		int offset = 0;
		while(offset < chessLayout.getBoardLength()) {
			
			int placedOffset = chessLayout.placePieceInNextAvailablePosition(chessPiece, offset);
			if( placedOffset == ChessLayout.NULL_OFFSET ) {
//				if(logger.isDebugEnabled())
//					logger.debug("Failed to complete this board (" + (piecesToPlace.size() - pieceIndex) + " pieces remaining):\n" + chessLayout.getLayoutText());
				return false; // could not find any available position for piece
			}
			else {
				if(completeLayout(completedLayouts, chessLayout.clone(), piecesToPlace, pieceIndex + 1) == false)
					return false; // give up this branch - backtrack
				
				// success - try next offset
				chessLayout.removeChessPiece(chessPiece);
				offset = placedOffset + 1;
			}
		}
		return false;
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
