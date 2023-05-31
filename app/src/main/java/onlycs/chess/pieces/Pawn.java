package onlycs.chess.pieces;

import java.util.ArrayList;
import onlycs.chess.rustish.Option;
import onlycs.chess.types.Board;
import onlycs.chess.types.Color;
import onlycs.chess.types.Move;
import onlycs.chess.types.PieceEnum;
import onlycs.chess.types.Position;
import onlycs.chess.types.Move.MoveModifier;

public class Pawn extends Piece {
	private Color color;
	private Position position;


	public Pawn(Color color, Position position) {
		this.color = color;
		this.position = position;
	}

	public Color getColor() {
		return color.getSame();
	}

	public Position getPosition() {
		return position.clone();
	}

	public Pawn clone() {
		return new Pawn(color, position);
	}

	public PieceEnum getType() {
		return PieceEnum.Pawn;
	}

	public void setPosition(Position position) {
		this.position = position.clone();
	}

	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> moves = new ArrayList<Move>();

		switch (color) {
			case White:
				Option<Position> up = position.up(1).ok();

				if (up.isSome()) {
					Position upPos = up.unwrap();
					if (board.at(upPos).getPiece().isNone()) {
						moves.add(new Move(position, upPos));

						if (position.rank == 2) {
							Option<Position> up2 = upPos.up(1).ok();

							if (up2.isSome()) {
								Position up2Pos = up2.unwrap();
								if (board.at(up2Pos).getPiece().isNone()) {
									moves.add(new Move(position, up2Pos,
											MoveModifier.PawnDoubleMove));
								}
							}
						}
					}
				}

				Option<Position> upLeft = position.up(1).ok().map(x -> {
					return x.left(1).ok();
				}).flatten();

				if (upLeft.isSome()) {
					Position upLeftPos = upLeft.unwrap();
					if (board.at(upLeftPos).getPiece().isSome()
							&& board.at(upLeftPos).getPiece().unwrap().getColor() == Color.Black) {
						moves.add(new Move(position, upLeftPos, MoveModifier.Capture));
					}
				}

				Option<Position> upRight = position.up(1).ok().map(x -> {
					return x.right(1).ok();
				}).flatten();

				if (upRight.isSome()) {
					Position upRightPos = upRight.unwrap();
					if (board.at(upRightPos).getPiece().isSome()
							&& board.at(upRightPos).getPiece().unwrap().getColor() == Color.Black) {
						moves.add(new Move(position, upRightPos, MoveModifier.Capture));
					}
				}

				break;
			case Black:
				Option<Position> down = position.down(1).ok();

				if (down.isSome()) {
					Position downPos = down.unwrap();
					if (board.at(downPos).getPiece().isNone()) {
						moves.add(new Move(position, downPos));

						if (position.rank == 7) {
							Option<Position> down2 = downPos.down(1).ok();

							if (down2.isSome()) {
								Position down2Pos = down2.unwrap();
								if (board.at(down2Pos).getPiece().isNone()) {
									moves.add(new Move(position, down2Pos,
											MoveModifier.PawnDoubleMove));
								}
							}
						}
					}
				}

				Option<Position> downLeft = position.down(1).ok().map(x -> {
					return x.left(1).ok();
				}).flatten();

				if (downLeft.isSome()) {
					Position downLeftPos = downLeft.unwrap();
					if (board.at(downLeftPos).getPiece().isSome() && board.at(downLeftPos)
							.getPiece().unwrap().getColor() == Color.White) {
						moves.add(new Move(position, downLeftPos, MoveModifier.Capture));
					}
				}

				Option<Position> downRight = position.down(1).ok().map(x -> {
					return x.right(1).ok();
				}).flatten();

				if (downRight.isSome()) {
					Position downRightPos = downRight.unwrap();
					if (board.at(downRightPos).getPiece().isSome() && board.at(downRightPos)
							.getPiece().unwrap().getColor() == Color.White) {
						moves.add(new Move(position, downRightPos, MoveModifier.Capture));
					}
				}

				break;
			default:
				break;
		}

		return moves;
	}
}
