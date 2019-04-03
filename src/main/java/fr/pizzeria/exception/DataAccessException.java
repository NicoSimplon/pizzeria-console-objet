package fr.pizzeria.exception;

public class DataAccessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5113706241918927403L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}	

}
