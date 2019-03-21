package fr.pizzeria.menu;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.StockageException;

public class SupprimerPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException {
		
		try {
			
			System.out.println("Suppression d’une pizza");
			System.out.println("Veuillez choisir le code de la pizza à supprimer :");
			String codePizz = scanner.nextLine();
			
			dao.deletePizza(codePizz);
			
			if(codePizz.length() < 3){
				throw new DeletePizzaException("Le code doit contenir au moins 3 caractères");
			}
			
		} catch (DeletePizzaException e) {
			e.printStackTrace();
		}
		
	}

}
