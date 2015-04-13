package net.martins.samples.chess;

public class Queen implements ChessPiece {

	public Queen() {
	}

	public boolean isHarmlessAt(ChessBoardLayout layout, int column, int row) {
		return true;
	}

}
