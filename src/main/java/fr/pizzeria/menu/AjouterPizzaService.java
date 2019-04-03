package fr.pizzeria.menu;

import java.sql.SQLException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Ajoute une nouvelle pizza avec les caractéristiques renseignées par l'utilisateur
 * 
 * @author Nicolas
 *
 */
public class AjouterPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException, SQLException {

		System.out.println("Ajout d’une nouvelle pizza");
		System.out.println("Veuillez saisir le code :");
		String code = scanner.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace) :");
		String nom = scanner.nextLine();
		System.out.println("Veuillez saisir le prix :");
		String prix = scanner.nextLine();
		double convertPrix = Double.parseDouble(prix);
		System.out.println("Veuillez choisir la catégorie (Viande, Poisson ou Sans Viande) :");
		String type = scanner.nextLine();
		CategoriePizza categorie = null;
		CategoriePizza[] cate = CategoriePizza.values();
		boolean cateBool = false;

		for (CategoriePizza s : cate) {
			if (s.getType().equals(type)) {
				cateBool = true;
				categorie = s;
			}
		}

		if (convertPrix < 0) {
			throw new SavePizzaException("Vous ne pouvez pas fixer un prix négatif");
		} else if (code.length() < 3) {
			throw new SavePizzaException("Le code doit contenir au moins 3 caractères");
		} else if (nom.length() < 5) {
			throw new SavePizzaException("Le nom doit contenir au moins 5 caractères");
		} else if (cateBool == false) {
			throw new UpdatePizzaException("Vous avez rentré un mauvais nom de catégorie");
		}

		dao.saveNewPizza(new Pizza(code, nom, convertPrix, categorie));

	}

}
