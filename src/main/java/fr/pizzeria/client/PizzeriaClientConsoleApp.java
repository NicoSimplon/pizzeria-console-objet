package fr.pizzeria.client;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.ClientDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.menuclient.MenuClientFactory;

public class PizzeriaClientConsoleApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzeriaClientConsoleApp.class);
	
	private static ClientDao dao = new ClientDao();
	
	private static Scanner scan = new Scanner(System.in);

	public static void pizzaMenu() {
		LOGGER.info("");
		LOGGER.info("***** Pizzeria Client *****");
		LOGGER.info("1. S'inscrire");
		LOGGER.info("2. Se Connecter");
		LOGGER.info("99. Sortir");
	}

	public static void main(String[] args) {
		
		MenuClientFactory menu = new MenuClientFactory();
		
		while(true) {
			
			pizzaMenu();
			
			String choix = scan.nextLine();
			
			try {
				if (choix.equals("1")) {
					
					menu.create("inscription").executeUC(scan, dao);
					
				} else if (choix.equals("2")) {
					
					menu.create("connexion").executeUC(scan, dao);
					
				} else if (choix.equals("99")) {
					
					LOGGER.info("Aurevoir ☹");
					scan.close();
					break;
					
				}
			} catch (StockageException e) {
				LOGGER.error(e.getMessage());
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}

	}

}
