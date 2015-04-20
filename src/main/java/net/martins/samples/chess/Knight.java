package net.martins.samples.chess;

import java.util.Iterator;

public class Knight extends AbstractChessPiece {

	public Knight() {
	}

	public boolean isAttackedAtPosition(ChessLayout layout, int column, int row) {
		Iterator<ChessPiece> piecesIterator = layout.piecesIterator();
		while(piecesIterator.hasNext()) {
			ChessPiece piece = piecesIterator.next();
			int drow = Math.abs(piece.getRow() - row);
			int dcolumn = Math.abs(piece.getColumn() - column);
			if((drow == 2 && dcolumn == 1) || (drow == 1 && dcolumn == 2))
				return true;
		}
		return false;
	}

	public String getSymbol() {
		return "N";
	}

	public boolean canAttackPosition(ChessLayout layout, int column, int row) {
		int drow = Math.abs(getRow() - row);
		int dcolumn = Math.abs(getColumn() - column);
		if((drow == 2 && dcolumn == 1) || (drow == 1 && dcolumn == 2))
			return true;
		return false;
	}

}
