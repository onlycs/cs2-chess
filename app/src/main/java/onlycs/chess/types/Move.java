package onlycs.chess.types;


public class Move {
	public Position from;
	public Position to;
	public MoveModifier[] modifiers;


	public enum MoveModifier {
		Capture, EnPassant, Promotion, CastleQueenside, CastleKingside, PawnDoubleMove
	}

	public Move(Position from, Position to, MoveModifier... modifiers) {
		this.from = from;
		this.to = to;
		this.modifiers = modifiers;
	}
}
