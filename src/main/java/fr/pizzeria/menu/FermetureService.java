package fr.pizzeria.menu;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.console.PizzeriaAdminConsoleApp;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaJpaDao;
import fr.pizzeria.exception.StockageException;

public class FermetureService extends MenuService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);
	
	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException, SQLException {
		
		if(dao instanceof PizzaJpaDao){
			
			dao.destroyEmFactory();
			LOGGER.info("Les services de JPA ont été cloturés avec succès");

		} else {
			
			LOGGER.info("PizzaJpaDao n'est pas activé, vous ne pouvez donc pas clôturer l'EntityManagerFactory");
			
		}

	}

}
