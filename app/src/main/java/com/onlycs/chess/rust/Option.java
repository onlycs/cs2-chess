package com.onlycs.chess.rust;

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

	public Result<T, Error> context(Error error) {
		if (isSome) {
			return Result.Ok(value);
		} else {
			return Result.Err(error);
		}
	}
}
