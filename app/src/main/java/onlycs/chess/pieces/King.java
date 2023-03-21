package onlycs.chess.pieces;

import java.util.ArrayList;
import onlycs.chess.rustish.Option;
import onlycs.chess.types.Board;
import onlycs.chess.types.Color;
import onlycs.chess.types.Move;
import onlycs.chess.types.PieceEnum;
import onlycs.chess.types.Position;
import onlycs.chess.types.Move.MoveModifier;

public class King extends Piece {
	private Color color;
	private Position position;

	public King(Color color, Position position) {
		this.color = color;
		this.position = position;
	}

	public Color getColor() {
		return color.getSame();
	}

	public Position getPosition() {
		return position.clone();
	}

	public King clone() {
		return new King(color, position);
	}

	public void setPosition(Position position) {
		this.position = position.clone();
	}

	public PieceEnum getType() {
		return PieceEnum.King;
	}

	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> moves = new ArrayList<Move>();

		ArrayList<Option<Position>> possible = new ArrayList<Option<Position>>();

		possible.add(position.up(1).ok());
		possible.add(position.down(1).ok());
		possible.add(position.left(1).ok());
		possible.add(position.right(1).ok());
		possible.add(position.up(1).ok().map(x -> {
			return x.left(1).ok();
		}).flatten());
		possible.add(position.up(1).ok().map(x -> {
			return x.right(1).ok();
		}).flatten());
		possible.add(position.down(1).ok().map(x -> {
			return x.left(1).ok();
		}).flatten());
		possible.add(position.down(1).ok().map(x -> {
			return x.right(1).ok();
		}).flatten());

		for (Option<Position> pos : possible) {
			if (pos.isSome()) {
				Position p = pos.unwrap();
				if (board.at(p).getPiece().isNone()) {
					moves.add(new Move(position, p));
				} else if (board.at(p).getPiece().unwrap().getColor() != color) {
					moves.add(new Move(position, p, MoveModifier.Capture));
				}
			}
		}

		return moves;
	}
}
