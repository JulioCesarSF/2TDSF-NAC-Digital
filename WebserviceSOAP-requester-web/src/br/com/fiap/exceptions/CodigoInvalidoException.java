package br.com.fiap.exceptions;

public class CodigoInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CodigoInvalidoException() {
		super();

	}

	public CodigoInvalidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public CodigoInvalidoException(String message, Throwable cause) {
		super(message, cause);

	}

	public CodigoInvalidoException(String message) {
		super(message);

	}

	public CodigoInvalidoException(Throwable cause) {
		super(cause);

	}

}
