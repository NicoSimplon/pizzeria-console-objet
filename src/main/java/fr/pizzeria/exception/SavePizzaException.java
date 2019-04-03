package fr.pizzeria.exception;

public class SavePizzaException extends StockageException {
	
	private static final long serialVersionUID = -7742084499561652364L;

	public SavePizzaException(String msg) {
		super(msg);
	}

}
