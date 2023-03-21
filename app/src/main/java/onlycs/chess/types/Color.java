package onlycs.chess.types;

public enum Color {
	Black, White;

	public Color getOpposite() {
		if (this == Black) {
			return White;
		} else {
			return Black;
		}
	}

	public String toString() {
		if (this == Black) {
			return "Black";
		} else {
			return "White";
		}
	}

	public char toChar() {
		if (this == Black) {
			return 'b';
		} else {
			return 'w';
		}
	}

	public static Color fromChar(char c) {
		if (c == 'b') {
			return Black;
		} else {
			return White;
		}
	}

	public Color getSame() {
		switch (this) {
			case Black:
				return Black;
			case White:
				return White;
			default:
				return null;
		}
	}
}
