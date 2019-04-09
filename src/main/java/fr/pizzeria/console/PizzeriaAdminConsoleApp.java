package fr.pizzeria.console;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaBddDao;
import fr.pizzeria.dao.PizzaJpaDao;
import fr.pizzeria.exception.DataAccessException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.menu.MenuFactory;

public class PizzeriaAdminConsoleApp {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);
	
	// Instance unique du scanner
	private static Scanner scan = new Scanner(System.in);
	
	// Instance du DAO
//	 private static IPizzaDao dao = new PizzaMemDao();
//	private static IPizzaDao dao = new PizzaBddDao();
	private static IPizzaDao dao = new PizzaJpaDao();
//	private static IPizzaDao dao = new PizzaFileDao();

	// Affichage du menu
	public static void pizzaMenu() {

		System.out.println("");
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		
		if (PizzaBddDao.class.isInstance(dao)) {
			System.out.println("5. Initialiser la base de données");			
		}
		
		System.out.println("99. Sortir");
	}

	public static void main(String[] args) throws SQLException, StockageException
	{
		
		MenuFactory menu = new MenuFactory();
		
		while (true) {
			
			PizzeriaAdminConsoleApp.pizzaMenu();
			String choix = scan.nextLine();
			
			try {
				if (choix.equals("1")) {
					menu.create("lister").executeUC(scan, dao);
				}
				else if (choix.equals("2")) {
					menu.create("ajouter").executeUC(scan, dao);
				}
				else if (choix.equals("3")) {
					menu.create("modifier").executeUC(scan, dao);
				}
				else if (choix.equals("4")) {
					menu.create("supprimer").executeUC(scan, dao);
				}
				else if (choix.equals("5")) {
					
					if (PizzaBddDao.class.isInstance(dao)) {
						menu.create("initialiser").executeUC(scan, dao);
					}

				}
				else if (choix.equals("99")) {
					System.out.println("Aurevoir ☹");
					
					if (PizzaJpaDao.class.isInstance(dao)) {
						menu.create("fermeture").executeUC(scan, dao);
					}
					
					scan.close();
					break;
				}
			} 
			catch (StockageException|DataAccessException e) {
				
				LOGGER.error(e.getMessage());

			}
			catch (Exception e) {
				
				LOGGER.error(e.getMessage());
				
			}
			
		}
		

	}

}
