package net.martins.samples.chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Holds the information about the placement of chess pieces in a chess board.<p>
 * The positions are not identifies by column and row, but by an offset from 
 * row zero and column zero to width and height.
 * 
 * @author cemartins
 */
public class ChessLayout {
	
	private static final ChessPiece NULL_PIECE = new NullPiece();

	private int width;
	
	private int boardLength;
	
	/**
	 * Maps an offset to a chess piece in the board.<p>
	 * Offset 0 corresponds to column=0, row=0; Offset 1 corresponds to column=1, row=0
	 */
	private Map<Integer, ChessPiece> pieceOffsets;

	public ChessLayout(int width, int height) {

		this.width = width;
		this.boardLength = width * height;
		
		this.pieceOffsets = new HashMap<Integer, ChessPiece>();
	}
	
	public Iterator<ChessPiece> piecesIterator() {
		return pieceOffsets.values().iterator();
	}
	
	public Iterator<Integer> freeCellsIterator() {
		List<Integer> freeCells = new ArrayList<Integer>(boardLength);
		for(int i = 0; i < boardLength; i++)
			if(pieceOffsets.get(Integer.valueOf(i)) == null)
				freeCells.add(Integer.valueOf(i));
		return freeCells.iterator();
	}
	
	/**
	 * Place a chess piece on the board at position column, row
	 * @param piece Chess piece to place
	 * @param offset Column position place (0 is first column)
	 */
	public void setChessPiece(ChessPiece piece, int offset) {
		if(offset >= boardLength)
			throw new ArrayIndexOutOfBoundsException("Piece fall outside the chess board");
		piece.placeAt(offset % width, offset / width);
		pieceOffsets.put(offset, piece);
	}

	public void printBoard() {
		int height = boardLength / width;
		int offset = 0;
		for(int r = 0; r < height ; r++) {
			System.out.print("|");
			for(int c = 0; c < width; c++)
				System.out.print("-");
			System.out.println("|");
			System.out.print("|");
			for(int c = 0; c < width; c++) {
				ChessPiece chessPiece = pieceOffsets.get(Integer.valueOf(offset));
				if(chessPiece == null)
					chessPiece = NULL_PIECE;
				System.out.print(chessPiece.getSymbol());
			}
			System.out.println("|");
		}
		System.out.print("|");
		for(int c = 0; c < width; c++)
			System.out.print("-");
		System.out.println("|");
	}
}
