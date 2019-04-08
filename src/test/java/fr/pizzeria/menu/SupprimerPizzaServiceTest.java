package fr.pizzeria.menu;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class SupprimerPizzaServiceTest {

	/** Création d'une "Rule" qui va permettre
	* de substituer le System.in utilisé par le Scanner
	* par un mock: systemInMock */
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Test
	public void testexecuteUC() {
	
		IPizzaDao dao = new PizzaMemDao();
		
		dao.saveNewPizza(new Pizza(9, "CDE", "PizzaBidon", 15.0, CategoriePizza.VIANDE));
		
		SupprimerPizzaService service = new SupprimerPizzaService();
		
		systemInMock.provideLines("CDE");

		try {
			service.executeUC(new Scanner(System.in), dao);
		} catch (StockageException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Assert.assertFalse(dao.pizzaExists("CDE"));

	}
	
}
