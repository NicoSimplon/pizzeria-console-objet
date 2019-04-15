package fr.pizzeria.menuclient;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.client.PizzeriaClientConsoleApp;
import fr.pizzeria.dao.ClientDao;
import fr.pizzeria.exception.StockageException;

public class ConnexionClientService extends MenuClientService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzeriaClientConsoleApp.class);

	@Override
	public void executeUC(Scanner scanner, ClientDao dao) throws StockageException {
		
		LOGGER.info("Connexion");
		LOGGER.info(" ");
		
		LOGGER.info("Veuillez renseigner votre email");
		LOGGER.info("-------------------------------");
		String email = scanner.nextLine();
		
		LOGGER.info("Veuillez renseigner votre mot de passe");
		LOGGER.info("--------------------------------------");
		String password = scanner.nextLine();
		
		if (dao.isValid(email, password)) {
			LOGGER.info("Connecté");
		}
		
		

	}

}
