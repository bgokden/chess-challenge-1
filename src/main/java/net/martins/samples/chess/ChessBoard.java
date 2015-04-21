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
	public List<ChessLayout> searchLayouts() {

		List<ChessLayout> foundLayouts = new ArrayList<ChessLayout>();
		
		// get a list of all the possible permutations based on the chess pieces available
		
		List<List<ChessPiece>> permutations = new ArrayList<List<ChessPiece>>();
		
		buildChessPiecePermutations(permutations, new ArrayList<ChessPiece>(), new ArrayList(Arrays.asList(pieces)));
		
		// for each permutation try and find chess board layouts starting from every position on the board
		
		int boardLength = width * height;
		
		for(List<ChessPiece> permutation : permutations) {
			
			for(int offset = 0; offset < boardLength; offset ++) {
				ChessLayout chessLayout = new ChessLayout(width, height);
				List<ChessPiece> piecesToPlace = new ArrayList(permutation);
				chessLayout.placeChessPieceAtPosition(piecesToPlace.remove(0), offset);

				if(buildLayout(chessLayout, piecesToPlace))
					foundLayouts.add(chessLayout);
				else
					break;
			}
			
		}

		return foundLayouts;
	}
	
	/**
	 * Tries to place all the pieces in a layout
	 * @param chessLayout
	 * @param piecesToPlace
	 * @return
	 */
	private boolean buildLayout(ChessLayout chessLayout, List<ChessPiece> piecesToPlace) {
		if(piecesToPlace.size() == 0)
			return true;
		ChessPiece chessPiece = piecesToPlace.get(0);
		if( ! chessLayout.placePieceInNextAvailableCell(chessPiece) )
			return false;
		else {
			piecesToPlace.remove(chessPiece);
			return buildLayout(chessLayout, piecesToPlace);
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
