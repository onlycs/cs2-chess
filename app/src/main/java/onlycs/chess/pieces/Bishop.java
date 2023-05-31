package onlycs.chess.pieces;

import java.util.ArrayList;
import onlycs.chess.rustish.Option;
import onlycs.chess.types.Board;
import onlycs.chess.types.Color;
import onlycs.chess.types.Move;
import onlycs.chess.types.PieceEnum;
import onlycs.chess.types.Position;
import onlycs.chess.types.Square;
import onlycs.chess.types.Move.MoveModifier;

public class Bishop extends Piece {
	private Color color;
	private Position position;

	public Bishop(Color color, Position position) {
		this.color = color;
		this.position = position;
	}

	public Color getColor() {
		return color.getSame();
	}

	public Position getPosition() {
		return position.clone();
	}

	public Bishop clone() {
		return new Bishop(color, position);
	}

	public void setPosition(Position position) {
		this.position = position.clone();
	}

	public PieceEnum getType() {
		return PieceEnum.Bishop;
	}

	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> moves = new ArrayList<Move>();

		// get all possible moves
		// up and right
		for (int i = 1; i < 8; i++) {
			final Integer innerI = Integer.valueOf(i);
			Option<Position> optpos = position.up(i).ok().map(x -> {
				return x.right(innerI).ok();
			}).flatten();

			if (optpos.isNone()) {
				break;
			}

			Position pos = optpos.unwrap();

			Square sq = board.at(pos);

			if (sq.getPiece().isSome()) {
				Piece pc = sq.getPiece().unwrap();

				if (pc.getColor() != color) {
					moves.add(new Move(position.clone(), pos.clone(), MoveModifier.Capture));
				}

				break;
			} else {
				moves.add(new Move(position.clone(), pos.clone()));
			}
		}

		// up and left
		for (int i = 1; i < 8; i++) {
			final Integer innerI = Integer.valueOf(i);
			Option<Position> optpos = position.up(i).ok().map(x -> {
				return x.left(innerI).ok();
			}).flatten();

			if (optpos.isNone()) {
				break;
			}

			Position pos = optpos.unwrap();

			Square sq = board.at(pos);

			if (sq.getPiece().isSome()) {
				Piece pc = sq.getPiece().unwrap();

				if (pc.getColor() != color) {
					moves.add(new Move(position.clone(), pos.clone(), MoveModifier.Capture));
				}

				break;
			} else {
				moves.add(new Move(position.clone(), pos.clone()));
			}
		}

		// down and right
		for (int i = 1; i < 8; i++) {
			final Integer innerI = Integer.valueOf(i);
			Option<Position> optpos = position.down(i).ok().map(x -> {
				return x.right(innerI).ok();
			}).flatten();

			if (optpos.isNone()) {
				break;
			}

			Position pos = optpos.unwrap();

			Square sq = board.at(pos);

			if (sq.getPiece().isSome()) {
				Piece pc = sq.getPiece().unwrap();

				if (pc.getColor() != color) {
					moves.add(new Move(position.clone(), pos.clone(), MoveModifier.Capture));
				}

				break;
			} else {
				moves.add(new Move(position.clone(), pos.clone()));
			}
		}

		// down and left
		for (int i = 1; i < 8; i++) {
			final Integer innerI = Integer.valueOf(i);
			Option<Position> optpos = position.down(i).ok().map(x -> {
				return x.left(innerI).ok();
			}).flatten();

			if (optpos.isNone()) {
				break;
			}

			Position pos = optpos.unwrap();

			Square sq = board.at(pos);

			if (sq.getPiece().isSome()) {
				Piece pc = sq.getPiece().unwrap();

				if (pc.getColor() != color) {
					moves.add(new Move(position.clone(), pos.clone(), MoveModifier.Capture));
				}

				break;
			} else {
				moves.add(new Move(position.clone(), pos.clone()));
			}
		}

		return moves;
	}
}
