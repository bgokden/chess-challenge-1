package net.martins.samples.chess;

import java.util.Iterator;

public class Rook extends AbstractChessPiece {

	public Rook() {
	}

	public boolean isAttackedAtPosition(ChessLayout layout, int column, int row) {
		Iterator<ChessPiece> piecesIterator = layout.piecesIterator();
		while(piecesIterator.hasNext()) {
			ChessPiece piece = piecesIterator.next();
			if(piece.getRow() == row || piece.getColumn() == column)
				return true;
		}
		return false;
	}

	public String getSymbol() {
		return "R";
	}

	public boolean canAttackPosition(ChessLayout layout, int column, int row) {
		if(getRow() == row || getColumn() == column)
			return true;
		return false;
	}

}
