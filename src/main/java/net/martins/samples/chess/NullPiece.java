package net.martins.samples.chess;

public class NullPiece extends AbstractChessPiece {

	public boolean isAttackedAtPosition(ChessLayout layout, int column, int row) {
		return true;
	}

	public String getSymbol() {
		return " ";
	}

	public boolean canAttackPosition(ChessLayout layout, int column, int row) {
		return false;
	}

}
