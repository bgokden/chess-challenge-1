package net.martins.samples.chess;

public class Knight implements ChessPiece {

	public Knight() {
	}

	public boolean isHarmlessAt(ChessBoardLayout layout, int column, int row) {
		return true;
	}

}
