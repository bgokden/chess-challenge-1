package net.martins.samples.chess;


public class Rook extends AbstractChessPiece {

	public String getSymbol() {
		return "R";
	}

	public boolean canAttackPosition(int column, int row) {
		if(getRow() == row || getColumn() == column)
			return true;
		return false;
	}

}
