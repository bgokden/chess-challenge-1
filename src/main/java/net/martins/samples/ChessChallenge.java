package net.martins.samples;

import java.util.Map;

import net.martins.samples.chess.Bishop;
import net.martins.samples.chess.ChessBoard;
import net.martins.samples.chess.ChessLayout;
import net.martins.samples.chess.ChessPiece;
import net.martins.samples.chess.King;
import net.martins.samples.chess.Knight;
import net.martins.samples.chess.Queen;

public class ChessChallenge {


	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard(7, 7, new ChessPiece[] {new King(), new King(), new Queen(), new Queen(), new Bishop(), new Bishop(), new Knight()});
		
		long begin = System.currentTimeMillis();

		Map<Integer, ChessLayout> layouts = chessBoard.searchLayouts();
		
		long end = System.currentTimeMillis();
		
		System.out.println("Found " + layouts.size() + " unique configurations in " + (end - begin) + "ms");
	}

}
