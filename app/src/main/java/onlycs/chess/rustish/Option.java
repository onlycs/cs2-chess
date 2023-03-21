package onlycs.chess.rustish;

public class Option<T> {
	private T value;
	private boolean isSome;

	private Option(T value, boolean isSome) {
		this.value = value;
		this.isSome = isSome;
	}

	public static <T> Option<T> Some(T value) {
		return new Option<T>(value, true);
	}

	public static <T> Option<T> None() {
		return new Option<T>(null, false);
	}

	public boolean isSome() {
		return isSome;
	}

	public boolean isNone() {
		return !isSome;
	}

	public T unwrap() {
		if (!isSome) {
			throw new RuntimeException("Tried to unwrap a None value");
		}
		return value;
	}

	public T unwrapOr(T defaultValue) {
		if (!isSome) {
			return defaultValue;
		}
		return value;
	}

	public Result<T> context(Error error) {
		if (isSome) {
			return Result.Ok(value);
		} else {
			return Result.Err(error);
		}
	}

	public <U> Option<U> map(OptionMapArgs<T, U> args) {
		if (isSome) {
			return Option.Some(args.map(value));
		} else {
			return Option.None();
		}
	}

	// assume T is Option<K> (so this is Option<Option<K>>), return Option<K>, mapping the inner
	// option to a new option
	@SuppressWarnings("hiding")
	public <K, T extends Option<K>> Option<K> flatten() {
		if (isSome) {
			@SuppressWarnings("unchecked")
			Option<K> inner = (Option<K>) value;
			return inner;
		} else {
			return Option.None();
		}
	}
}
