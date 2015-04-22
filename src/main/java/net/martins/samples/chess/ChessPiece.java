package net.martins.samples.chess;

public interface ChessPiece {

	/**
	 * Checks whether this piece can attack the specified position in the specified layout
	 * @param layout
	 * @param column
	 * @param row
	 * @return
	 */
	public boolean canAttackPosition(int column, int row);
	
	/**
	 * @return the character representation of this chess piece
	 */
	public String getSymbol();
	
	
	public void setColumn(int column);
	
	/**
	 * @return The column where this piece is placed in the layout (0 is first column)
	 */
	public int getColumn();

	
	public void setRow(int row);
	
	/**
	 * @return The row where this piece is placed in the layout (0 is first row)
	 */
	public int getRow();
}
