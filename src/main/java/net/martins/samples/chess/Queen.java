package net.martins.samples.chess;

public class Queen extends AbstractChessPiece {

	public Queen() {
	}

	public boolean isAttackedAtPosition(ChessLayout layout, int column, int row) {
		return true;
	}

	public String getSymbol() {
		return "Q";
	}

	public boolean canAttackPosition(ChessLayout layout, int column, int row) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
