package onlycs.chess.pieces;

import java.util.ArrayList;
import onlycs.chess.rustish.Option;
import onlycs.chess.types.Board;
import onlycs.chess.types.Color;
import onlycs.chess.types.Move;
import onlycs.chess.types.PieceEnum;
import onlycs.chess.types.Position;
import onlycs.chess.types.Square;


public class Rook extends Piece {
	private Color color;
	private Position position;

	public Rook(Color color, Position position) {
		this.color = color;
		this.position = position;
	}

	public Color getColor() {
		return color.getSame();
	}

	public Position getPosition() {
		return position.clone();
	}

	public Rook clone() {
		return new Rook(color, position);
	}

	public void setPosition(Position position) {
		this.position = position.clone();
	}

	public PieceEnum getType() {
		return PieceEnum.Rook;
	}

	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> moves = new ArrayList<Move>();

		// up
		for (int i = 1; i < 8; i++) {
			Option<Position> upPos = position.up(i).ok();

			if (upPos.isNone()) {
				break;
			}

			Square sq = board.at(upPos.unwrap());

			if (sq.getPiece().isSome()) {
				if (sq.getPiece().unwrap().getColor() != color) {
					moves.add(new Move(position, upPos.unwrap()));
				}

				break;
			} else {
				moves.add(new Move(position, upPos.unwrap()));
			}
		}

		// down
		for (int i = 1; i < 8; i++) {
			Option<Position> downPos = position.down(i).ok();

			if (downPos.isNone()) {
				break;
			}

			Square sq = board.at(downPos.unwrap());

			if (sq.getPiece().isSome()) {
				if (sq.getPiece().unwrap().getColor() != color) {
					moves.add(new Move(position, downPos.unwrap()));
				}

				break;
			} else {
				moves.add(new Move(position, downPos.unwrap()));
			}
		}

		// left
		for (int i = 1; i < 8; i++) {
			Option<Position> leftPos = position.left(i).ok();

			if (leftPos.isNone()) {
				break;
			}

			Square sq = board.at(leftPos.unwrap());

			if (sq.getPiece().isSome()) {
				if (sq.getPiece().unwrap().getColor() != color) {
					moves.add(new Move(position, leftPos.unwrap()));
				}

				break;
			} else {
				moves.add(new Move(position, leftPos.unwrap()));
			}
		}

		// right
		for (int i = 1; i < 8; i++) {
			Option<Position> rightPos = position.right(i).ok();

			if (rightPos.isNone()) {
				break;
			}

			Square sq = board.at(rightPos.unwrap());

			if (sq.getPiece().isSome()) {
				if (sq.getPiece().unwrap().getColor() != color) {
					moves.add(new Move(position, rightPos.unwrap()));
				}

				break;
			} else {
				moves.add(new Move(position, rightPos.unwrap()));
			}
		}

		return moves;
	}
}
