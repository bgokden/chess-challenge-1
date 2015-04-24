package net.martins.samples.chess;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Results {

	private static final Logger logger = Logger.getLogger(Results.class);

	private Map<Integer, ChessLayout> completedLayouts;
	private int attempts;
	private int duplicates;

	public Results() {
		completedLayouts = new HashMap<Integer, ChessLayout>();
		attempts = 0;
		duplicates = 0;
	}

	public void addLayout(ChessLayout chessLayout) {
		attempts++;
		chessLayout.setCompletionAttempt(attempts);
		Integer hash = Integer.valueOf(chessLayout.hashCode());
		ChessLayout previous = completedLayouts.get(hash);

		if (previous == null) {
			completedLayouts.put(hash, chessLayout);
			if (logger.isDebugEnabled()) {
				logger.debug("Layout # " + attempts + " (hash="+hash+"):");
				logger.debug("\n" + chessLayout.getLayoutText());
//				Scanner in = new Scanner(System.in);
//				in.nextLine();
			}
		} else {
			duplicates++;
			if (logger.isDebugEnabled()) {
				logger.error("Layout # " + attempts + " (hash="+hash+") is a duplicate of layout " + previous.getCompletionAttempt());
				logger.error("\n" + chessLayout.getLayoutText());
//				Scanner in = new Scanner(System.in);
//				in.nextLine();
			}
		}
	}
	
	public void printLayouts(PrintStream out) {
		for(ChessLayout chessLayout : completedLayouts.values()) {
			out.println(chessLayout.getLayoutText());
		}
	}

	public int getNumUniqueLayouts() {
		return completedLayouts.size();
	}

	public int getNumAttempts() {
		return attempts;
	}

	public int getNumDuplicates() {
		return duplicates;
	}

}
