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
	 * Checks whether there is a chess piece in a diagonal from the specified point (defined by column, row) and up to the specified radius.
	 * @param column Column of the starting point (0 is first column)
	 * @param row Row of the starting point (0 is first row)
	 * @param radius
	 * @return
	 */
	public boolean hasColidingDiagonal(int column, int row, int radius) {
		return false;
	}
	
	public boolean hasColidingPrependicular(int column, int row, int radius) {
		return false;
	}
	
	public boolean hasColidingKnightMoves(int column, int row) {
		return false;
	}
}
