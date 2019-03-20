package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.model.Pizza;

/**
 * Système de CRUD
 * 
 * @author Nicolas
 *
 */
public class PizzaFileDao implements IPizzaDao {
	
	private Pizza[] tableauPizza;
	private GestionFichier gestionFichier;
	
	public PizzaFileDao() {
		initialisation();
		gestionFichier = new GestionFichier ();
		lecture ();
	}
	
	public void initialisation ()
	{
		this.tableauPizza = new Pizza[] {
			new Pizza(0, "PEP", "Pépéroni", 12.50),
			new Pizza(1, "MAR", "Margherita", 14.00),
			new Pizza(2, "REIN", "La Reine", 11.50),
			new Pizza(3, "FRO", "Les 4 fromages", 12.00),
			new Pizza(4, "CAN", "La canibale", 12.50),
			new Pizza(5, "SAV", "La savoyarde", 13.00),
			new Pizza(6, "ORI", "L'orientale", 13.50),
			new Pizza(7, "IND", "L'indienne", 14.00)
		};
	}

	@Override
	public Pizza[] findAllPizzas() {
		return this.tableauPizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		
		boolean vide = false;
		
		for (int i = 0; i < this.tableauPizza.length; i++) {
			if (this.tableauPizza[i] == null) {
				this.tableauPizza[i] = pizza;
				vide = true;
				break;
			}
			else {
				vide = false;
			}
		}
		
		if (vide == false) {
			Pizza[] tab2 = new Pizza[this.tableauPizza.length + 1];
			
			for (int j = 0; j < this.tableauPizza.length; j++) {
				tab2[j] = this.tableauPizza[j];
			}
			
			this.tableauPizza = tab2;
			
			this.tableauPizza[this.tableauPizza.length - 1] = pizza;
			
		}
		
		ecriture();
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
	
		for (int i = 0; i < this.tableauPizza.length; i++) {
			if (this.tableauPizza[i].getCode().equals(codePizza)) {
				tableauPizza[i] = pizza;
			}
		}
		
		ecriture();
		
	}

	@Override
	public void deletePizza(String codePizza) {
		
		for (int i = 0; i < this.tableauPizza.length; i++) {
			if (this.tableauPizza[i].getCode().equals(codePizza)) {
				this.tableauPizza[i] = null;
			}
		}
		
		ecriture();
		
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		
		int idx = 0;
		
		for (int i = 0; i < this.tableauPizza.length; i++) {
			if (this.tableauPizza[i].getCode().equals(codePizza)) {
				idx = i;
			}
		}
		
		return this.tableauPizza[idx];
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		boolean exist = false;
		for (int i = 0; i < this.tableauPizza.length; i++) {
			if (this.tableauPizza[i].getCode().equals(codePizza)) {
				exist = true;
				break;
			}
		}
		return exist;
	}

	/**
	 * Ecrit dans un fichier .txt les données des pizzas
	 */
	public void ecriture () {
		
		List <String> listString = new ArrayList<String> ();
		
		for (Pizza pizza : tableauPizza)
			if (pizza != null)
				listString.add(pizza.toSave());
		
		gestionFichier.ecriture(listString);
	}
	
	/**
	 * Lis les données des pizzas dans un fichier .txt
	 */
	public void lecture () {
		
		List <String> listString = gestionFichier.lecture();
		
		for (String s : listString)
		{
			String [] pizza = s.split(",");
			tableauPizza[Integer.parseInt(pizza[0])] = new Pizza (Integer.parseInt(pizza[0]), pizza[1], pizza[2], Double.parseDouble(pizza[3]));
		}
			
	}
	
}
