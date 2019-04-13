package fr.pizzeria.menu;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.console.PizzeriaAdminConsoleApp;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

/**
 * Affiche la liste des pizzas
 * 
 * @author Nicolas
 *
 */
public class ListerPizzasService extends MenuService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);
	
	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException, SQLException 
	{
		
		LOGGER.info("Liste des pizzas :");
		LOGGER.info("------------------");

		List<Pizza> tableauPizza = dao.findAllPizzas();
		
		for (int i = 0; i < tableauPizza.size(); i++) {
			String pizza = tableauPizza.get(i).toString();
			LOGGER.info(pizza);												
			
		}
		
	}

}
