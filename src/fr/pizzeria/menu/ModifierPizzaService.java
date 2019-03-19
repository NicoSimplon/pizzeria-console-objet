package fr.pizzeria.menu;

import java.util.Scanner;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, PizzaMemDao dao) {
		
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
		
		Pizza nvPizza = new Pizza(newCodePizz, newNomPizz, prix2);
		
		dao.updatePizza(codePizz, nvPizza);
		
	}

}
