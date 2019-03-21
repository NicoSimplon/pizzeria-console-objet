package fr.pizzeria.exception;

public class DeletePizzaException extends StockageException {

	private static final long serialVersionUID = 2769821308584269270L;

	public DeletePizzaException(String msg) {
		super(msg);
	}

}
