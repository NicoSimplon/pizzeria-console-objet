package fr.pizzeria.menu;

import java.util.Scanner;
import fr.pizzeria.dao.PizzaMemDao;

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
	 */
	abstract void executeUC(Scanner scanner, PizzaMemDao dao);
	
}
