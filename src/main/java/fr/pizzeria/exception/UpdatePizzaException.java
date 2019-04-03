package fr.pizzeria.exception;

public class UpdatePizzaException extends StockageException {

	private static final long serialVersionUID = -5682149591857278420L;

	public UpdatePizzaException(String msg) {
		super(msg);
	}

}
