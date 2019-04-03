package fr.pizzeria.menu;

import java.sql.SQLException;
import java.util.Scanner;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * Gestion du menu
 * 
 * @author Nicolas
 *
 */
public abstract class MenuService {
	
	/**
	 * Gestion du menu en fonction des intéractions de l'utilisateur
	 * 
	 * @param scanner Objet scanner permettant de gérer les intéractions avec l'utilisateur
	 * @throws StockageException 
	 * @throws SQLException 
	 */
	public abstract void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException, SQLException;
	
}
