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

	public List<ChessLayout> searchLayouts() {
		List<ChessLayout> foundLayouts = new ArrayList<ChessLayout>();
		
		List<ChessPiece> piecesToPlace = new ArrayList(Arrays.asList(pieces));
		ChessLayout chessLayout = new ChessLayout(width, height);

		if(drawLayout(chessLayout, piecesToPlace) != null)
			chessLayout.printBoard();

		return foundLayouts;
	}
	
	private ChessLayout drawLayout(ChessLayout chessLayout, List<ChessPiece> piecesToPlace) {
		if(piecesToPlace.size() == 0)
			return chessLayout;
		ChessPiece chessPiece = piecesToPlace.get(0);
		if( ! chessLayout.placePieceInNextAvailableCell(chessPiece) ) {
			System.out.println("Failed layout");
			chessLayout.printBoard();
			return null;
		}
		else {
			piecesToPlace.remove(chessPiece);
			return drawLayout(chessLayout, piecesToPlace);
		}
	}
}
