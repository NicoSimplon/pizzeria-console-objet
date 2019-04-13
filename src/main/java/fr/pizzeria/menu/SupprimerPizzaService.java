package fr.pizzeria.menu;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.console.PizzeriaAdminConsoleApp;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.StockageException;


/**
 * Supprime la pizza sélectionnée
 * 
 * @author Nicolas
 *
 */
public class SupprimerPizzaService extends MenuService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException, SQLException {

		LOGGER.info("Suppression d’une pizza");
		LOGGER.info("Veuillez choisir le code de la pizza à supprimer :");
		String codePizz = scanner.nextLine();

		
		if (codePizz.length() < 3) {
			throw new DeletePizzaException("Le code doit contenir au moins 3 caractères");
		}
		
		if (dao.pizzaExists(codePizz)) {
			dao.deletePizza(codePizz);
			LOGGER.info("La pizza a été supprimée avec succès");
		} else {
			throw new DeletePizzaException("Aucune pizza existante n'a le code renseigné");
		}

	}

}
