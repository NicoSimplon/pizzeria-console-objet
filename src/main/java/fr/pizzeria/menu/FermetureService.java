package fr.pizzeria.menu;

import java.sql.SQLException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaJpaDao;
import fr.pizzeria.exception.StockageException;

public class FermetureService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException, SQLException {
		
		if(dao instanceof PizzaJpaDao){
			
			dao.destroyEmFactory();

		} else {
			
			System.out.println("PizzaJpaDao n'est pas activé, vous ne pouvez donc pas clôturer l'EntityManagerFactory");
			
		}

	}

}
