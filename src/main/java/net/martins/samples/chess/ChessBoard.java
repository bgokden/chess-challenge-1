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

	private List<ChessLayout> searchLayouts() {
		List<ChessLayout> foundLayouts = new ArrayList<ChessLayout>();
		
		List<ChessPiece> searchPieces = Arrays.asList(pieces);

		return foundLayouts;
	}
}
