package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * Système de CRUD avec écriture dans un fichier
 * 
 * @author Nicolas
 *
 */
public class PizzaFileDao implements IPizzaDao{

	private void initializer() {
		Pizza[] tableauPizza = new Pizza[] {
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
	
	private void lireFichierRech(Pizza tab[],String nFichier){
	    String NomFichier = "C:\\TEMP\\workspace\\pizzeria-console-objet" + nFichier;
	    try{
	    	BufferedReader in  = new BufferedReader(new FileReader(NomFichier));
	    	String line;
	        int i = 0;
	        while ((line = in.readLine()) != null) {
	          tab[i] = Integer.parseInt(line.trim());
	        i++;
	        }
	        in.close();
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
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
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
	
		for (int i = 0; i < this.tableauPizza.length; i++) {
			if (this.tableauPizza[i].getCode().equals(codePizza)) {
				tableauPizza[i] = pizza;
			}
		}
		
	}

	@Override
	public void deletePizza(String codePizza) {
		
		for (int i = 0; i < this.tableauPizza.length; i++) {
			if (this.tableauPizza[i].getCode().equals(codePizza)) {
				this.tableauPizza[i] = null;
			}
		}
		
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

}
