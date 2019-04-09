package fr.pizzeria.menu;

import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public class ListerPizzasServiceTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(AjouterPizzaService.class);
	
	@Test
	public void testexecuteUC() throws StockageException, SQLException {
		
		IPizzaDao dao = Mockito.mock(IPizzaDao.class);

		LOG.info("Etant donné une instance de ListerPizzasService");
		ListerPizzasService service = new ListerPizzasService();
		
		LOG.info("Lorsque la méthode executeUC est exécutée");
		service.executeUC(new Scanner(System.in), dao);
		
		LOG.info("Alors la méthode findAllPizzas est bien appelée");
		Mockito.verify(dao).findAllPizzas();
		
	}

}
