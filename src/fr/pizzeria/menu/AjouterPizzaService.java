package fr.pizzeria.menu;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException {

		try {

			System.out.println("Ajout d’une nouvelle pizza");
			System.out.println("Veuillez saisir le code :");
			String code = scanner.nextLine();
			System.out.println("Veuillez saisir le nom (sans espace) :");
			String nom = scanner.nextLine();
			System.out.println("Veuillez saisir le prix :");
			String prix = scanner.nextLine();
			double convertPrix = Double.parseDouble(prix);
			
			if(convertPrix < 0){
				throw new SavePizzaException("Vous ne pouvez pas fixer un prix négatif");
			} else if (code.length() < 3) {
				throw new SavePizzaException("Le code doit contenir au moins 3 caractères");
			} else if (nom.length() < 5) {
				throw new SavePizzaException("Le nom doit contenir au moins 5 caractères");
			}

			dao.saveNewPizza(new Pizza(code, nom, convertPrix));

		} catch (SavePizzaException e) {
			e.printStackTrace();
		}

	}

}
