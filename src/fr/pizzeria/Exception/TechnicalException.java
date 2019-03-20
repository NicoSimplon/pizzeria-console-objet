package fr.pizzeria.Exception;

/**
 * Exception de type Runtime pour la gestion de fichiers .txt
 * 
 * @author Nicolas
 *
 */
public class TechnicalException extends RuntimeException{

	public TechnicalException(String msg) {
		super(msg);
	}
	
	public TechnicalException(Exception e) {
		super(e);
	}
	
}
