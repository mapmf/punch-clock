package com.marcos.punchclock.services.exceptions;

public class InvalidPuchClockException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidPuchClockException(String msg) {
		super(msg);
	}

}
