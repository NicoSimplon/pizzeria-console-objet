package fr.pizzeria.menu;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException {

		try {

			System.out.println("Mise à jour d’une pizza");
			System.out.println("Veuillez choisir le code de la pizza à modifier.");
			String codePizz = scanner.nextLine();
			System.out.println("Veuillez choisir le nouveau code :");
			String newCodePizz = scanner.nextLine();
			System.out.println("Veuillez choisir le nouveau nom (sans espace) :");
			String newNomPizz = scanner.nextLine();
			System.out.println("Veuillez choisir le nouveau prix :");
			String newPrixPizz = scanner.nextLine();
			double prix2 = Double.parseDouble(newPrixPizz);
			System.out.println("Veuillez choisir la catégorie (Viande, Poisson ou Sans Viande) :");
			String type = scanner.nextLine();
			CategoriePizza newCategorie = null;
			CategoriePizza[] cate = CategoriePizza.values();
			boolean cateBool = false;

			for (CategoriePizza s : cate) {
				if (s.getType().equals(type)) {
					cateBool = true;
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
			} else if (cateBool == false) {
				throw new UpdatePizzaException("Vous avez rentré un mauvais nom de catégorie");
			}

			dao.updatePizza(codePizz, nvPizza);

		} catch (UpdatePizzaException e) {
			e.printStackTrace();
		}

	}

}
