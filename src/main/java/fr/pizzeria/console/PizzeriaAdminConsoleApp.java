package fr.pizzeria.console;

import java.util.Scanner;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaFileDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.menu.*;

public class PizzeriaAdminConsoleApp {

	// Instance unique du scanner
	public static Scanner scan = new Scanner(System.in);
	// Instance du DAO
	 public static IPizzaDao dao = new PizzaMemDao();
	// public static IPizzaDao dao = new PizzaFileDao();

	// Affichage du menu
	public static void pizzaMenu() {
		System.out.println("");
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");
	}

	public static void main(String[] args) {
		
		MenuFactory menu = new MenuFactory();
		
		while (true) {
			
			PizzeriaAdminConsoleApp.pizzaMenu();
			String choix = scan.nextLine();
			
			try {
				if (choix.equals("1")) {
					menu.create("lister").executeUC(scan, dao);
				}
				else if (choix.equals("2")) {
					menu.create("ajouter").executeUC(scan, dao);
				}
				else if (choix.equals("3")) {
					menu.create("modifier").executeUC(scan, dao);
				}
				else if (choix.equals("4")) {
					menu.create("supprimer").executeUC(scan, dao);
				}
				else if (choix.equals("99")) {
					System.out.println("Aurevoir ☹");
					scan.close();
					break;
				}
			} catch (StockageException e) {
				
				e.getStackTrace();

			}
			
		}
		

	}

}
