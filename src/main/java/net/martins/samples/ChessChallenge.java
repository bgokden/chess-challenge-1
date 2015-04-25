package net.martins.samples;

import net.martins.samples.chess.Bishop;
import net.martins.samples.chess.ChessBoard;
import net.martins.samples.chess.ChessPiece;
import net.martins.samples.chess.King;
import net.martins.samples.chess.Knight;
import net.martins.samples.chess.Queen;
import net.martins.samples.chess.Results;

public class ChessChallenge {


	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard(7, 7, new ChessPiece[] {new King(), new King(), new Queen(), new Queen(), new Bishop(), new Bishop(), new Knight()});
//		ChessBoard chessBoard = new ChessBoard(3, 3, new ChessPiece[] {new King(), new King(), new Rook()});
//		ChessBoard chessBoard = new ChessBoard(4, 4, new ChessPiece[] {new Rook(), new Rook(), new Knight(), new Knight(), new Knight(), new Knight()});
		
		long begin = System.currentTimeMillis();

		Results results = chessBoard.searchLayouts();
		
		long end = System.currentTimeMillis();
		
		System.out.println("Found " + results.getNumUniqueLayouts() + " unique configurations in " + (end - begin) + "ms");
		System.out.println("      " + results.getNumAttempts() + " attempts");
		System.out.println("      " + results.getNumDuplicates() + " duplicates");
		
		results.printLayouts(System.out);
	}

}
