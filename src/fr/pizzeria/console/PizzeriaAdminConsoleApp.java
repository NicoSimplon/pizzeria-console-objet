package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {
	
	// Affichage du menu
	public static void pizzaMenu() {
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");
	}

	public static void main(String[] args) {

		// Intanciation de la classe Scanner
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			PizzeriaAdminConsoleApp.pizzaMenu();
			int choix = scan.nextInt();
			
			if (choix == 1) {
				System.out.println("Liste des pizzas");
			}
			else if (choix == 2) {
				System.out.println("Ajout d’une nouvelle pizza");
						}
			else if (choix == 3) {
				System.out.println("Mise à jour d’une pizza");
			}
			else if (choix == 4) {
				System.out.println("Suppression d’une pizza");
			}
			else if (choix == 99) {
				System.out.println("Aurevoir ☹");
				break;
			}
			
		}
		

	}

}
