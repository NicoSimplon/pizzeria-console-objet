package fr.pizzeria.menu;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.console.PizzeriaAdminConsoleApp;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Modifie la pizza sélectionnée
 * 
 * @author Nicolas
 *
 */
public class ModifierPizzaService extends MenuService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException, SQLException {

		LOGGER.info("Mise à jour d’une pizza");
		LOGGER.info("Veuillez choisir le code de la pizza à modifier.");
		String codePizz = scanner.nextLine();
		LOGGER.info("Veuillez choisir le nouveau code :");
		String newCodePizz = scanner.nextLine();
		LOGGER.info("Veuillez choisir le nouveau nom (sans espace) :");
		String newNomPizz = scanner.nextLine();
		LOGGER.info("Veuillez choisir le nouveau prix :");
		String newPrixPizz = scanner.nextLine();
		double prix2 = Double.parseDouble(newPrixPizz);
		LOGGER.info("Veuillez choisir la catégorie (Viande, Poisson ou Sans Viande) :");
		String type = scanner.nextLine();
		CategoriePizza newCategorie = null;
		CategoriePizza[] cate = CategoriePizza.values();
		String searchCategorie = "inconnu";

		for (CategoriePizza s : cate) {
			if (s.getType().equals(type)) {
				searchCategorie = "connu";
				newCategorie = s;
			}
		}

		Pizza nvPizza = new Pizza(newCodePizz, newNomPizz, prix2, newCategorie);

		if (prix2 < 0) {
			throw new UpdatePizzaException("Vous ne pouvez pas fixer un prix négatif");
		} else if (newCodePizz.length() < 3) {
			throw new UpdatePizzaException("Le code doit contenir au moins 3 caractères");
		} else if (newNomPizz.length() < 5) {
			throw new UpdatePizzaException("Le nom doit contenir au moins 5 caractères");
		} else if (searchCategorie.equals("inconnu")) {
			throw new UpdatePizzaException("Vous avez rentré un mauvais nom de catégorie");
		}

		if (dao.pizzaExists(codePizz)) {
			dao.updatePizza(codePizz, nvPizza);
			LOGGER.info("La pizza a été modifiée avec succès");
		} else {
			throw new UpdatePizzaException("Aucune pizza existante n'a le code renseigné");
		}

	}

}
