package net.martins.samples.chess;

public class Rook implements ChessPiece {

	public Rook() {
	}

	public boolean isHarmlessAt(ChessBoardLayout layout, int column, int row) {
		return true;
	}

}
