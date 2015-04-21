package net.martins.samples.chess;


public class Bishop extends AbstractChessPiece {

	public String getSymbol() {
		return "B";
	}

	public boolean canAttackPosition(int column, int row) {
		if(Math.abs(getRow() - row) == Math.abs(getColumn() - column))
			return true;
		return false;
	}

}
