package fr.pizzeria.menu;

import java.util.Scanner;
import fr.pizzeria.dao.PizzaMemDao;

public class SupprimerPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, PizzaMemDao dao) {
		
		System.out.println("Suppression d’une pizza");
		System.out.println("Veuillez choisir le code de la pizza à supprimer :");
		String codePizz = scanner.nextLine();
		
		dao.deletePizza(codePizz);
		
	}

}
