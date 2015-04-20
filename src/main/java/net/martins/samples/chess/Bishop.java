package net.martins.samples.chess;

import java.util.Iterator;

public class Bishop extends AbstractChessPiece {

	public Bishop() {
	}

	public boolean isAttackedAtPosition(ChessLayout layout, int column, int row) {
		Iterator<ChessPiece> piecesIterator = layout.piecesIterator();
		while(piecesIterator.hasNext()) {
			ChessPiece piece = piecesIterator.next();
			if(Math.abs(piece.getRow() - row) == Math.abs(piece.getColumn() - column))
				return true;
		}
		return false;
	}

	public String getSymbol() {
		return "B";
	}

	public boolean canAttackPosition(ChessLayout layout, int column, int row) {
		if(Math.abs(getRow() - row) == Math.abs(getColumn() - column))
			return true;
		return false;
	}

}
