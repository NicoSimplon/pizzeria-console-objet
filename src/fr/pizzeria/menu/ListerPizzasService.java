package fr.pizzeria.menu;

import java.util.Scanner;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, PizzaMemDao dao) {
		
		System.out.println("Liste des pizzas");

		Pizza[] tableauPizza = dao.findAllPizzas();
		
		for (int i = 0; i < tableauPizza.length; i++) {
			if (tableauPizza[i] != null) {
				System.out.println(tableauPizza[i]);												
			}
		}
		
	}

}
