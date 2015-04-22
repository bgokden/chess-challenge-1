package net.martins.samples.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Holds the information about the placement of chess pieces in a chess board.<p>
 * The positions are not identifies by column and row, but by an offset from 
 * row zero and column zero to width and height.
 * 
 * @author cemartins
 */
public class ChessLayout {
	
	public static final ChessPiece NULL_PIECE = new NullPiece();
	
	public static final int NULL_OFFSET = -1;

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
		
		this.pieceOffsets = new HashMap<Integer, ChessPiece>(boardLength);
	}
	
	public int getBoardLength() {
		return boardLength;
	}
	
	/**
	 * Place a chess piece on the board at position column, row.<p>
	 * Does not verify whether the piece can be attacked buy other present pieces or that it can attack any of them.
	 * @param piece Chess piece to place
	 * @param column Column position place (0 is first column)
	 * @param row Row position place (0 is first row)
	 */
	public void placeChessPieceAtPosition(ChessPiece piece, int column, int row) {
		piece.setColumn(column);
		piece.setRow(row);
		pieceOffsets.put(row * width + column, piece);
	}

	/**
	 * @param piece
	 * @param offset
	 * @see {@link ChessLayout#placeChessPieceAtPosition(ChessPiece, int, int)}
	 */
	public void placeChessPieceAtPosition(ChessPiece piece, int offset) {
		int column = offset % width;
		int row = offset / width;
		placeChessPieceAtPosition(piece, column, row);
	}

	public void removeChessPiece(ChessPiece piece) {
		int offset = piece.getColumn() + width * piece.getRow();
		if(pieceOffsets.remove(Integer.valueOf(offset)) == null)
			throw new IllegalArgumentException("Could not find piece in this layout");
	}
	
	
	public int placePieceInNextAvailablePosition(ChessPiece piece) {
		return placePieceInNextAvailablePosition(piece, 0);
	}

	/**
	 * @param piece Chess piece to place
	 * @param startingOffset Offset from the top left corned of the chess board to start looking for an available position
	 * @return the offset at wich the piece was placed or NULL_OFFSET if the piece could not be placed.
	 */
	public int placePieceInNextAvailablePosition(ChessPiece piece, int startingOffset) {
		
		for(int offset = startingOffset; offset < boardLength; offset++)
			if(pieceOffsets.get(Integer.valueOf(offset)) == null) {
				int column = offset % width;
				int row = offset / width;
				if(canPlacePieceAtPosition(piece, column, row)) {
					placeChessPieceAtPosition(piece, column, row);
					return offset;
				}
			}

		return NULL_OFFSET;
	}

	private boolean canPlacePieceAtPosition(ChessPiece piece, int column, int row) {
		
		for(ChessPiece laidPiece : pieceOffsets.values()) {
			if(laidPiece.canAttackPosition(column, row))
				return false;
			piece.setColumn(column);
			piece.setRow(row);
			if(piece.canAttackPosition(laidPiece.getColumn(), laidPiece.getRow()))
				return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int h = 0;
		for(Entry<Integer, ChessPiece> entry : pieceOffsets.entrySet())
			h += entry.getKey().intValue() * (int) entry.getValue().getSymbol().charAt(0);
		return h;
	}
	
	public String getLayoutText() {
		StringBuilder sb = new StringBuilder();

		int height = boardLength / width;
		int offset = 0;
		for(int r = 0; r < height ; r++) {
			for(int c = 0; c < width; c++) {
				sb.append("|");
				sb.append("---");
			}
			sb.append("|\n");
			for(int c = 0; c < width; c++) {
				sb.append("| ");
				ChessPiece chessPiece = pieceOffsets.get(Integer.valueOf(offset++));
				if(chessPiece == null)
					chessPiece = NULL_PIECE;
				sb.append(chessPiece.getSymbol());
				sb.append(" ");
			}
			sb.append("|\n");
		}
		for(int c = 0; c < width; c++) {
			sb.append("|");
			sb.append("---");
		}
		sb.append("|\n");

		return sb.toString();
	}

	public void printBoard() {
		System.out.println(getLayoutText());
	}
}
