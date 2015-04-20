package net.martins.samples.chess;

public interface ChessPiece {

	/**
	 * Checks whether this piece can be attacked at a specified position by any other pieces already in the layout.
	 * @param layout
	 * @param column
	 * @param row
	 * @return true if this piece cannot attack any of the other pieces
	 */
	public boolean isAttackedAtPosition(ChessLayout layout, int column, int row);
	
	/**
	 * Checks whether this piece can attack the specified position in the specified layout
	 * @param layout
	 * @param column
	 * @param row
	 * @return
	 */
	public boolean canAttackPosition(ChessLayout layout, int column, int row);
	
	/**
	 * @return the character representation of this chess piece
	 */
	public String getSymbol();
	
	public void placeAt(int column, int row);
	
	public int getColumn();
	
	public int getRow();
}
