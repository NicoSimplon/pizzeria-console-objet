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
//	 private static IPizzaDao dao = new PizzaBddDao();
	private static IPizzaDao dao = new PizzaJpaDao();
//	 private static IPizzaDao dao = new PizzaFileDao();

	// Affichage du menu
	public static void pizzaMenu() {

		LOGGER.info("");
		LOGGER.info("***** Pizzeria Administration *****");
		LOGGER.info("1. Lister les pizzas");
		LOGGER.info("2. Ajouter une nouvelle pizza");
		LOGGER.info("3. Mettre à jour une pizza");
		LOGGER.info("4. Supprimer une pizza");

		if (PizzaBddDao.class.isInstance(dao)) {
			LOGGER.info("5. Initialiser la base de données");
		}

		LOGGER.info("99. Sortir");
	}

	public static void main(String[] args) throws SQLException, StockageException {

		MenuFactory menu = new MenuFactory();

		while (true) {

			PizzeriaAdminConsoleApp.pizzaMenu();
			String choix = scan.nextLine();

			try {
				if (choix.equals("1")) {
					menu.create("lister").executeUC(scan, dao);
				} else if (choix.equals("2")) {
					menu.create("ajouter").executeUC(scan, dao);
				} else if (choix.equals("3")) {
					menu.create("modifier").executeUC(scan, dao);
				} else if (choix.equals("4")) {
					menu.create("supprimer").executeUC(scan, dao);
				} else if (choix.equals("5")) {

					if (PizzaBddDao.class.isInstance(dao)) {
						menu.create("initialiser").executeUC(scan, dao);
					}

				} else if (choix.equals("99")) {
					LOGGER.info("Aurevoir ☹");

					if (PizzaJpaDao.class.isInstance(dao)) {
						menu.create("fermeture").executeUC(scan, dao);
					}

					scan.close();
					break;
				}
			} catch (StockageException | DataAccessException e) {

				LOGGER.error(e.getMessage());

			} catch (Exception e) {

				LOGGER.error(e.getMessage());

			}

		}

	}

}
