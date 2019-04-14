package fr.pizzeria.menuclient;

import java.util.Scanner;

import fr.pizzeria.dao.ClientDao;
import fr.pizzeria.exception.StockageException;

/**
 * Modèle abstrait pour créer des services du menu client
 * 
 * @author Nicolas
 *
 */
public abstract class MenuClientService {
	
	/**
	 * Gestion du menu en fonction des intéractions de l'utilisateur
	 * 
	 * @param scanner Objet scanner permettant de gérer les intéractions avec l'utilisateur
	 */
	public abstract void executeUC(Scanner scanner, ClientDao dao) throws StockageException;

}
