package net.martins.samples.chess;

public class King extends AbstractChessPiece {

	public King() {
	}

	public boolean isAttackedAtPosition(ChessLayout layout, int column, int row) {
		return true;
	}

	public String getSymbol() {
		return "K";
	}

	public boolean canAttackPosition(ChessLayout layout, int column, int row) {
		// TODO Auto-generated method stub
		return false;
	}

}
