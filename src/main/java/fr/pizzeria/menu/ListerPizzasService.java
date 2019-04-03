package fr.pizzeria.menu;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService  {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException, SQLException 
	{
		
		System.out.println("Liste des pizzas :");
		System.out.println("------------------");

		List<Pizza> tableauPizza = dao.findAllPizzas();
		
		for (int i = 0; i < tableauPizza.size(); i++) {

			System.out.println(tableauPizza.get(i));												
			
		}
		
	}

}
