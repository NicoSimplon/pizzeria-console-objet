package fr.pizzeria.exception;

/**
 * Exception de type Runtime pour la gestion de fichiers .txt
 * 
 * @author Nicolas
 *
 */
public class TechnicalException extends RuntimeException{

	private static final long serialVersionUID = -5332444671489236748L;

	public TechnicalException(String msg) {
		super(msg);
	}
	
	public TechnicalException(Exception e) {
		super(e);
	}
	
}
