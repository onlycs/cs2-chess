package onlycs.chess;

import java.util.Scanner;
import onlycs.chess.types.Board;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("\033[2J\f");

		System.out.println("Lowercase letters >> p << correspond to black pieces");
		System.out.println("Uppercase letters >> P << correspond to white pieces");
		System.out.println();
		System.out.println("				=====	Pieces	=====");
		System.out.println("		p = Pawn				n = Knight");
		System.out.println("		r = Rook				b = Bishop");
		System.out.println("		q = Queen				k = King");
		System.out.println();
		System.out.println("			Use q to quit at any prompt");

		Thread.sleep(2000);

		System.out.println("\033[2J");

		Scanner sc = new Scanner(System.in);
		Board board = Board.create().unwrap();

		while (true) {
			board.userMove(sc);
		}
	}
}
