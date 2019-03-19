package fr.pizzeria.console;

import java.util.Scanner;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.menu.*;

public class PizzeriaAdminConsoleApp {
	
	// Instance unique du scanner
	public static Scanner scan = new Scanner(System.in);
	// Instance du DAO
	public static PizzaMemDao dao = new PizzaMemDao();

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
		MenuService typeMenu;
		
		while (true) {
			
			PizzeriaAdminConsoleApp.pizzaMenu();
			String choix = scan.nextLine();
			
			if (choix.equals("1")) {
				typeMenu = menu.create("lister");
				typeMenu.executeUC(scan, dao);
			}
			else if (choix.equals("2")) {
				typeMenu = menu.create("ajouter");
				typeMenu.executeUC(scan, dao);
			}
			else if (choix.equals("3")) {
				typeMenu = menu.create("modifier");
				typeMenu.executeUC(scan, dao);
			}
			else if (choix.equals("4")) {
				typeMenu = menu.create("supprimer");
				typeMenu.executeUC(scan, dao);
			}
			else if (choix.equals("99")) {
				System.out.println("Aurevoir ☹");
				scan.close();
				break;
			}
			
		}
		

	}

}
