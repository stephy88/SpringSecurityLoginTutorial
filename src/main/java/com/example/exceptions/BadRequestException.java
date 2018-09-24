package com.example.exceptions;

public class BadRequestException extends Exception{

	private String message;

	public BadRequestException()
	{

	}

	public BadRequestException(String message)
	{
		super(message);
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
