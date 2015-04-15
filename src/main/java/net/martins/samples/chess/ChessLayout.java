package net.martins.samples.chess;

import java.util.HashMap;
import java.util.Map;

public class ChessLayout {

	private int width;
	
	private int maxOffset;
	
	/**
	 * Maps an offset to a chess piece in the board.<p>
	 * Offset 0 corresponds to column=0, row=0; Offset 1 corresponds to column=1, row=0
	 */
	private Map<Integer, ChessPiece> pieceOffsets;

	public ChessLayout(int width, int height) {

		this.width = width;
		this.maxOffset = width * height - 1;
		
		this.pieceOffsets = new HashMap<Integer, ChessPiece>();
	}
	
	/**
	 * Place a chess piece on the board at position column, row
	 * @param piece Chess piece to place
	 * @param column Column position place (0 is first column)
	 * @param row Row of the position to place (0 is first row)
	 */
	public void setChessPiece(ChessPiece piece, int column, int row) {
		int offset = width * row + column;
		if(offset > maxOffset)
			throw new ArrayIndexOutOfBoundsException("Piece fall outside the chess board");
		pieceOffsets.put(offset, piece);
	}
	

	/**
	 * Checks whether there is a chess piece in the board in a position diagonal to the specified point (defined by column, row).
	 * @param column Column position to check against (0 is first column)
	 * @param row Row of the position to check against (0 is first row)
	 * @return true if there is a piece diagonal to the specified position
	 */
	public boolean hasColidingDiagonal(int column, int row, int radius) {
		for(Integer offset : pieceOffsets.keySet()) {
			int row1 = offset.intValue() / width;
			int column1 = offset.intValue() % width;
			if(Math.abs(row1 - row) == Math.abs(column1 - column))
				return true;
		}
		return false;
	}
	
	/**
	 * Checks whether there is a piece in the board in a position perpendicular (same row or column) to the specified position.
	 * @param column Column position to check against (0 is first column)
	 * @param row Row of the position to check against (0 is first row)
	 * @return true if there is a piece perpendicular to the specified position
	 */
	public boolean hasColidingPrependicular(int column, int row) {
		for(Integer offset : pieceOffsets.keySet()) {
			int row1 = offset.intValue() / width;
			int column1 = offset.intValue() % width;
			if(row1 == row || column1 == column)
				return true;
		}
		return false;
	}
	
	/**
	 * Checks whether there is a piece in the board in a position that can be reached from the specified point by a knight
	 * @param column
	 * @param row
	 * @return
	 */
	public boolean hasColidingKnightMoves(int column, int row) {
		for(Integer offset : pieceOffsets.keySet()) {
			int drow = Math.abs(offset.intValue() / width - row);
			int dcolumn = Math.abs(offset.intValue() % width - column);
			if((drow == 2 && dcolumn == 1) || (drow == 1 && dcolumn == 2))
				return true;
		}
		return false;
	}
}
