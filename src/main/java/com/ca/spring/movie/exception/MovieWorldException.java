package com.ca.spring.movie.exception;

public class MovieWorldException extends Exception {

	private static final long serialVersionUID = 1L;

	public MovieWorldException(String message) {
		super(message);
	}

	public MovieWorldException(Throwable throwable) {
		super(throwable);
	}

	public MovieWorldException(String message, Throwable throwable) {
		super(message, throwable);
	}

}