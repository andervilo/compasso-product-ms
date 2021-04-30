package br.com.desafio.resources.exceptions;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardError implements Serializable {

	private static final long serialVersionUID = -1379274819713973978L;

	@JsonProperty("status_code")
	private int statusCode;

	private String message;

	public StandardError() {

	}

	public StandardError(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
