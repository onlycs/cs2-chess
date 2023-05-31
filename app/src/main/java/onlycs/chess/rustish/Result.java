package onlycs.chess.rustish;

public class Result<T> {
	private T value;
	private Error error;
	private boolean isOk;

	private Result(T value, Error error, boolean isOk) {
		this.value = value;
		this.error = error;
		this.isOk = isOk;
	}

	public static <T> Result<T> Ok(T value) {
		return new Result<T>(value, null, true);
	}

	public static <T> Result<T> Err(Error error) {
		return new Result<T>(null, error, false);
	}

	public boolean isOk() {
		return isOk;
	}

	public boolean isErr() {
		return !isOk;
	}

	public T unwrap() {
		if (!isOk) {
			throw new Error("Called unwrap on a Result that is an Err: " + error.getMessage());
		}
		return value;
	}

	public T unwrapOr(T defaultValue) {
		if (!isOk) {
			return defaultValue;
		}
		return value;
	}

	public Option<T> ok() {
		if (isOk) {
			return Option.Some(value);
		} else {
			return Option.None();
		}
	}

	public Option<Error> err() {
		if (isOk) {
			return Option.None();
		} else {
			return Option.Some(error);
		}
	}
}
