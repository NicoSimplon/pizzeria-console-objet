package fr.pizzeria.menuclient;

import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.client.PizzeriaClientConsoleApp;
import fr.pizzeria.dao.ClientDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Client;

public class InscriptionClientService extends MenuClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzeriaClientConsoleApp.class);

	@Override
	public void executeUC(Scanner scanner, ClientDao dao) throws StockageException {
		
		LOGGER.info("Veuillez renseigner votre nom");
		LOGGER.info("-----------------------------");
		String nom = scanner.nextLine();
		
		LOGGER.info("Veuillez renseigner votre prénom");
		LOGGER.info("--------------------------------");
		String prenom = scanner.nextLine();
		
		LOGGER.info("Veuillez renseigner votre email");
		LOGGER.info("-------------------------------");
		String email = scanner.nextLine();
		
		LOGGER.info("Veuillez renseigner votre mot de passe");
		LOGGER.info("--------------------------------------");
		String password = scanner.nextLine();
		byte[] hashPassword = DigestUtils.sha256(password);

		Client nouveauClient = new Client();
		nouveauClient.setNom(nom);
		nouveauClient.setPrenom(prenom);
		nouveauClient.setEmail(email);
		nouveauClient.setMotDePasse(hashPassword);
		
		dao.saveNewClient(nouveauClient);
		
	}

}
