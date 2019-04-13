package fr.pizzeria.menu;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.console.PizzeriaAdminConsoleApp;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Ajoute une nouvelle pizza avec les caractéristiques renseignées par l'utilisateur
 * 
 * @author Nicolas
 *
 */
public class AjouterPizzaService extends MenuService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException, SQLException {

		LOGGER.info("Ajout d’une nouvelle pizza");
		LOGGER.info("Veuillez saisir le code :");
		String code = scanner.nextLine();
		LOGGER.info("Veuillez saisir le nom (sans espace) :");
		String nom = scanner.nextLine();
		LOGGER.info("Veuillez saisir le prix :");
		String prix = scanner.nextLine();
		double convertPrix = Double.parseDouble(prix);
		LOGGER.info("Veuillez choisir la catégorie (Viande, Poisson ou Sans Viande) :");
		String type = scanner.nextLine();
		CategoriePizza categorie = null;
		CategoriePizza[] cate = CategoriePizza.values();

		String searchCategorie = "inconnu";
		
		for (CategoriePizza s : cate) {
			if (s.getType().equals(type)) {
				categorie = s;
				searchCategorie = "connu";
			}
		}

		if (convertPrix < 0) {
			throw new SavePizzaException("Vous ne pouvez pas fixer un prix négatif");
		} else if (code.length() < 3) {
			throw new SavePizzaException("Le code doit contenir au moins 3 caractères");
		} else if (nom.length() < 5) {
			throw new SavePizzaException("Le nom doit contenir au moins 5 caractères");
		} else if (searchCategorie.equals("inconnu")) {
			throw new SavePizzaException("Vous avez rentré un mauvais nom de catégorie");
		}

		dao.saveNewPizza(new Pizza(code, nom, convertPrix, categorie));
		LOGGER.info("La nouvelle pizza a été créée avec succès");

	}

}
