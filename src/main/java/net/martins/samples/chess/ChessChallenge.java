package net.martins.samples.chess;

public class ChessChallenge {

	public ChessChallenge() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard(7, 7, new ChessPiece[] {new King(), new King(), new Queen(), new Queen(), new Bishop(), new Bishop(), new Knight()});
	}

}
