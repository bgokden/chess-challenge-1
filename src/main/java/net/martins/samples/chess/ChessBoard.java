package net.martins.samples.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	public Results searchLayouts() {

		Results results = new Results();
		
		// get a list of all the unique permutations based on the chess pieces available (duplicates are discarded)
		Set<List<ChessPiece>> permutations = new HashSet<List<ChessPiece>>();
		buildChessPiecePermutations(permutations, new ArrayList<ChessPiece>(), new ArrayList(Arrays.asList(pieces)));
		
		if(logger.isDebugEnabled())
			logger.debug("Found " + permutations.size() + " permutations.");
		
		for(List<ChessPiece> permutation : permutations) {
			
				ChessLayout chessLayout = new ChessLayout(width, height);
				
				placePieceOnBoard(results, chessLayout, permutation, 0, 0);
		}

		return results;
	}

	
	
	/**
	 * Tries to place a piece on all possible positions of the chess board
	 * @param results where to store all completed board layouts
	 * @param chessLayout Chess board layout (may contain previously placed chess pieces)
	 * @param piecesToPlace List of all chess pieces to place on board
	 * @param pieceIndex index of the list of pieces of the chess piece to place
	 */
	private boolean placePieceOnBoard(Results results, ChessLayout chessLayout, List<ChessPiece> piecesToPlace, int pieceIndex, int startOffset) {
		if(pieceIndex == piecesToPlace.size()) {
			// no more chess pieces to place. store the completed layout
			results.addLayout(chessLayout);
			return true;
		}
		else {
			
			ChessPiece chessPiece = piecesToPlace.get(pieceIndex);
			int offset = startOffset;
			while(offset < chessLayout.getBoardLength()) {
				
				int placedOffset = chessLayout.placePieceInNextAvailablePosition(chessPiece, offset);
				if( placedOffset == ChessLayout.NULL_OFFSET ) 
					break;
				else {
					
					if(logger.isDebugEnabled())
						logger.debug("piece " + pieceIndex + " (" + chessPiece + ") moved");
					
					// try possible combinations using the remaining pieces, in the offsets ahead of this one.
					placePieceOnBoard(results, chessLayout.clone(), piecesToPlace, pieceIndex + 1, placedOffset + 1);
					
					// backtrack and try next offset
					chessLayout.removeChessPiece(chessPiece);
					offset = placedOffset + 1;
				}
			}
			return false;
		}

	}
	
	/**
	 * Find permutations of a list of chess pieces using a recurrent method.<p>
	 * Based on the algorithm by Eric Leschinski (http://www.ericleschinski.com/c/java_permutations_recursion/)
	 * @param permutations
	 * @param collect
	 * @param distrib
	 */
	private void buildChessPiecePermutations(Set<List<ChessPiece>> permutations, List<ChessPiece> collect, List<ChessPiece> distrib) {
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
