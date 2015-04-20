package net.martins.samples.chess;

public abstract class AbstractChessPiece implements ChessPiece {
	
	private int column = -1;
	private int row = -1;

	public AbstractChessPiece() {
	}
	
	public abstract String getSymbol();
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || ! ChessPiece.class.isAssignableFrom(obj.getClass()))
			return false;
		return getSymbol().equals(((ChessPiece)obj).getSymbol());
	}
	
	public void placeAt(int column, int row) {
		this.column = column;
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
}
