package net.martins.samples.chess;

public class NullPiece extends AbstractChessPiece {

	public String getSymbol() {
		return " ";
	}

	public boolean canAttackPosition(int column, int row) {
		return false;
	}

}
