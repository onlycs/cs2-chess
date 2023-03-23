/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package onlycs.chess;

import java.util.Scanner;
import onlycs.chess.types.Board;
import onlycs.chess.types.Move;
import onlycs.chess.types.Position;
import onlycs.chess.types.Square;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Board board = Board.create().unwrap();

		while (true) {
			board.userMove(sc);
		}
	}
}
