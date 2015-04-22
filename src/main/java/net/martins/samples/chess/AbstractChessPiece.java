package net.martins.samples.chess;

public abstract class AbstractChessPiece implements ChessPiece {
	
	private int column = -1;
	private int row = -1;

	public AbstractChessPiece() {
	}
	
	public abstract String getSymbol();
	
	@Override
	public String toString() {
		return getSymbol();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || ! ChessPiece.class.isAssignableFrom(obj.getClass()))
			return false;
		return getSymbol().equals(((ChessPiece)obj).getSymbol());
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getRow() {
		return row;
	}
}
