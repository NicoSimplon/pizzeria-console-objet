package fr.pizzeria.menu;

import java.sql.SQLException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public class InitialiserPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException, SQLException {
		
		dao.initialiserBdd();

	}

}
