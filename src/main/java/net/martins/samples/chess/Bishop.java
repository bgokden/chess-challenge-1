package net.martins.samples.chess;

public class Bishop implements ChessPiece {

	public Bishop() {
	}

	public boolean isHarmlessAt(ChessBoardLayout layout, int column, int row) {
		return true;
	}

}
