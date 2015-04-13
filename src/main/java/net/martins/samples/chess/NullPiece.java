package net.martins.samples.chess;

public class NullPiece implements ChessPiece {

	public boolean isHarmlessAt(ChessBoardLayout layout, int column, int row) {
		return true;
	}

}
