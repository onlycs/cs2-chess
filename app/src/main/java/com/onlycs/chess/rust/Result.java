package com.onlycs.chess.rust;

public class Result<T, E extends Error> {
	private T value;
	private E error;
	private boolean isOk;

	private Result(T value, E error, boolean isOk) {
		this.value = value;
		this.error = error;
		this.isOk = isOk;
	}

	public static <T, E extends Error> Result<T, E> Ok(T value) {
		return new Result<T, E>(value, null, true);
	}

	public static <T, E extends Error> Result<T, E> Err(E error) {
		return new Result<T, E>(null, error, false);
	}

	public boolean isOk() {
		return isOk;
	}

	public boolean isErr() {
		return !isOk;
	}

	public T unwrap() {
		if (error != null) {
			throw new RuntimeException("Tried to unwrap a Err value");
		}
		return value;
	}

	public T unwrapOr(T defaultValue) {
		if (error != null) {
			return defaultValue;
		}
		return value;
	}

	public Option<T> makeOption() {
		if (isOk) {
			return Option.Some(value);
		} else {
			return Option.None();
		}
	}

	public E unwrapErr() {
		if (isOk) {
			throw new RuntimeException("Tried to unwrap a Ok value");
		}
		return error;
	}
}
