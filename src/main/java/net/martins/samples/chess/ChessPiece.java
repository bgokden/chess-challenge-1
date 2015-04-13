package net.martins.samples.chess;

public interface ChessPiece {

	public boolean isHarmlessAt(ChessBoardLayout layout, int column, int row);
}
