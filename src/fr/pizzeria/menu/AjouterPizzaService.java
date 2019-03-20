package fr.pizzeria.menu;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) {
		
		System.out.println("Ajout d’une nouvelle pizza");
		System.out.println("Veuillez saisir le code :");
		String code = scanner.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace) :");
		String nom = scanner.nextLine();
		System.out.println("Veuillez saisir le prix :");
		String prix = scanner.nextLine();
		double convertPrix = Double.parseDouble(prix);
		
		dao.saveNewPizza(new Pizza(code, nom, convertPrix));
		
	}

}
