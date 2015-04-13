package net.martins.samples.chess;

public class King implements ChessPiece {

	public King() {
	}

	public boolean isHarmlessAt(ChessBoardLayout layout, int column, int row) {
		return true;
	}

}
