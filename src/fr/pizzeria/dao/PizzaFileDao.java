package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Système de CRUD avec écriture dans un fichier
 * 
 * @author Nicolas
 *
 */
public class PizzaFileDao implements IPizzaDao{


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
