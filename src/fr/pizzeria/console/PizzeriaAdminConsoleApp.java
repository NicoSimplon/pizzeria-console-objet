package fr.pizzeria.console;

import java.util.Scanner;
import fr.pizzeria.model.Pizza;

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
		
		// Initialisation du tableau de pizza
		Pizza[] tableauPizza = {
				new Pizza(0, "PEP", "Pépéroni", 12.50),
				new Pizza(1, "MAR", "Margherita", 14.00),
				new Pizza(2, "REIN", "La Reine", 11.50),
				new Pizza(3, "FRO", "Les 4 fromages", 12.00),
				new Pizza(4, "CAN", "La canibale", 12.50),
				new Pizza(5, "SAV", "La savoyarde", 13.00),
				new Pizza(6, "ORI", "L'orientale", 13.50),
				new Pizza(7, "IND", "L'indienne", 14.00)
		};

		// Intanciation de la classe Scanner
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			PizzeriaAdminConsoleApp.pizzaMenu();
			String choix = scan.nextLine();
			
			if (choix.equals("1")) {
				System.out.println("Liste des pizzas");
				
				// Affiche la liste des pizzas
				for (int i = 0; i < tableauPizza.length; i++) {
					if (tableauPizza[i] != null) {
						tableauPizza[i].affichage();												
					}
				}
				
			}
			else if (choix.equals("2")) {
				
				// Récupère les informations relatives à la nouvelle pizza
				System.out.println("Ajout d’une nouvelle pizza");
				System.out.println("Veuillez saisir le code :");
				String code = scan.nextLine();
				System.out.println("Veuillez saisir le nom (sans espace) :");
				String nom = scan.nextLine();
				System.out.println("Veuillez saisir le prix :");
				String prix = scan.nextLine();
				double convertPrix = Double.parseDouble(prix);
				boolean trou = true;
				
				for (int j = 0; j < tableauPizza.length; j++) {
					if (tableauPizza[j] == null) {
						tableauPizza[j] = new Pizza(code, nom, convertPrix);
						trou = true;
						break;
					}
					else {
						trou = false;
					}
				}
				
				if (trou == false) {
					Pizza[] tab2 = new Pizza[tableauPizza.length + 1];
					
					for (int i = 0; i < tableauPizza.length; i++) {
						tab2[i] = tableauPizza[i];
					}
					
					tableauPizza = tab2;
					
					tableauPizza[tableauPizza.length - 1] = new Pizza(code, nom, convertPrix);
				}
				
			}
			else if (choix.equals("3")) {
				System.out.println("Mise à jour d’une pizza");
				
				// Récupère le code de la pizza à modifier
				System.out.println("Veuillez choisir le code de la pizza à modifier.");
				String codePizz = scan.nextLine();
				
				// Récupère les information à insérer
				System.out.println("Veuillez choisir le nouveau code :");
				String newCodePizz = scan.nextLine();
				System.out.println("Veuillez choisir le nouveau nom (sans espace) :");
				String newNomPizz = scan.nextLine();
				System.out.println("Veuillez choisir le nouveau prix :");
				String newPrixPizz = scan.nextLine();
				double prix2 = Double.parseDouble(newPrixPizz);
				
				// Modifie les infos de la pizza dans le tableau
				for (int i = 0; i < tableauPizza.length; i++) {
					if (tableauPizza[i].getCode().equals(codePizz)) {
						tableauPizza[i].setCode(newCodePizz);
						tableauPizza[i].setLibelle(newNomPizz);
						tableauPizza[i].setPrix(prix2);
					}
				}
				
			}
			else if (choix.equals("4")) {
				System.out.println("Suppression d’une pizza");
				
				System.out.println("Veuillez choisir le code de la pizza à supprimer :");
				String codePizz = scan.nextLine();

				for (int i = 0; i < tableauPizza.length; i++) {
					if (tableauPizza[i].getCode().equals(codePizz)) {
						tableauPizza[i] = null;
					}
				}
				
			}
			else if (choix.equals("99")) {
				System.out.println("Aurevoir ☹");
				scan.close();
				break;
			}
			
		}
		

	}

}
