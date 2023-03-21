package onlycs.chess.pieces;

import java.util.ArrayList;
import onlycs.chess.types.Board;
import onlycs.chess.types.Color;
import onlycs.chess.types.Move;
import onlycs.chess.types.PieceEnum;
import onlycs.chess.types.Position;

public class Queen extends Piece {
	private Color color;
	private Position position;

	public Queen(Color color, Position position) {
		this.color = color;
		this.position = position;
	}

	public Color getColor() {
		return color.getSame();
	}

	public Position getPosition() {
		return position.clone();
	}

	public Queen clone() {
		return new Queen(color, position);
	}

	public void setPosition(Position position) {
		this.position = position.clone();
	}

	public PieceEnum getType() {
		return PieceEnum.Queen;
	}

	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> moves = new ArrayList<Move>();

		Rook selfAsRook = new Rook(color, position);
		Bishop selfAsBishop = new Bishop(color, position);

		moves.addAll(selfAsRook.getMoves(board.clone()));
		moves.addAll(selfAsBishop.getMoves(board.clone()));

		return moves;
	}
}
