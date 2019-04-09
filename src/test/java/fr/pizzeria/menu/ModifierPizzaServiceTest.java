package fr.pizzeria.menu;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaServiceTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(ModifierPizzaService.class);

	/** Création d'une "Rule" qui va permettre
	* de substituer le System.in utilisé par le Scanner
	* par un mock: systemInMock */
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Test
	public void testexecuteUC() throws StockageException, SQLException {
	
		IPizzaDao dao = Mockito.mock(IPizzaDao.class);
		
		Pizza pizza = new Pizza(9, "CDA", "PizzaBidon", 12.0, CategoriePizza.VIANDE);
				
		Mockito.when(dao.pizzaExists("CDE")).thenReturn(true);
		
		LOG.info("Etant donné les informations renseignées par l'utilisateur");
		systemInMock.provideLines("CDE", "CDA", "PizzaBidon", "12.0", "Viande");
		
		LOG.info("Etant donné une instance de ModifierPizzaService");
		ModifierPizzaService service = new ModifierPizzaService();
		
		LOG.info("Lorsque la méthode executeUC est exécutée");
		service.executeUC(new Scanner(System.in), dao);

		LOG.info("Alors la méthode updatePizza est bien appelée");
		Mockito.verify(dao).updatePizza("CDE", pizza);

	}
	
}
