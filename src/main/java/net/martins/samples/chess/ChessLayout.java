package net.martins.samples.chess;

import java.util.ArrayList;
import java.util.Collection;
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
		
		this.pieceOffsets = new HashMap<Integer, ChessPiece>(boardLength);
	}
	
	public Iterator<ChessPiece> piecesIterator() {
		return pieceOffsets.values().iterator();
	}
	
	/**
	 * Place a chess piece on the board at position column, row
	 * @param piece Chess piece to place
	 * @param column Column position place (0 is first column)
	 * @param row Row position place (0 is first row)
	 */
	public void placeChessPieceAtPosition(ChessPiece piece, int column, int row) {
		piece.placeAt(column, row);
		pieceOffsets.put(row * width + column, piece);
	}
	
	public void removeChessPiece(ChessPiece piece) {
		int offset = piece.getColumn() + width * piece.getRow();
		pieceOffsets.remove(Integer.valueOf(offset));
	}
	
	public boolean placePieceInNextAvailableCell(ChessPiece piece) {
		
		for(int offset = 0; offset < boardLength; offset++)
			if(pieceOffsets.get(Integer.valueOf(offset)) == null) {
				int column = offset % width;
				int row = offset / width;
				if(canPlacePieceAtPosition(piece, column, row)) {
					placeChessPieceAtPosition(piece, column, row);
					return true;
				}
			}

		return false;
	}
	
	private boolean canPlacePieceAtPosition(ChessPiece piece, int column, int row) {
		
		for(ChessPiece laidPiece : pieceOffsets.values()) {
			if(laidPiece.canAttackPosition(column, row))
				return false;
			piece.placeAt(column, row);
			if(piece.canAttackPosition(laidPiece.getColumn(), laidPiece.getRow()))
				return false;
		}
		return true;
	}

	public void printBoard() {
		int height = boardLength / width;
		int offset = 0;
		for(int r = 0; r < height ; r++) {
			for(int c = 0; c < width; c++) {
				System.out.print("|");
				System.out.print("-");
			}
			System.out.println("|");
			for(int c = 0; c < width; c++) {
				System.out.print("|");
				ChessPiece chessPiece = pieceOffsets.get(Integer.valueOf(offset++));
				if(chessPiece == null)
					chessPiece = NULL_PIECE;
				System.out.print(chessPiece.getSymbol());
			}
			System.out.println("|");
		}
		for(int c = 0; c < width; c++) {
			System.out.print("|");
			System.out.print("-");
		}
		System.out.println("|");
	}
}
