package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.console.PizzeriaAdminConsoleApp;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Système de CRUD
 * 
 * @author Nicolas
 *
 */
public class PizzaFileDao implements IPizzaDao {

	private List<Pizza> tableauPizza;
	private GestionFichier gestionFichier;
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);

	public PizzaFileDao() {
		// Initialisation de l'ArrayList
		this.tableauPizza = new ArrayList<>();

		// Initialisation du fichier save.txt
		gestionFichier = new GestionFichier("save.txt");
		
		if(gestionFichier.fichier.length() == 0){
			
			this.initialisation();
			ecriture();
		}
		else 
		{
			lecture();
		}
		
	}

	/**
	 * Initialise les données de base de la liste de pizzas
	 */
	public void initialisation() {
		tableauPizza.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza("REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza("FRO", "Les 4 fromages", 12.00, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza("CAN", "La canibale", 12.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
	}

	@Override
	public List<Pizza> findAllPizzas() {
		return this.tableauPizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {

		this.tableauPizza.add(pizza);

		ecriture();

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {

		for (int i = 0; i < this.tableauPizza.size(); i++) {
			if (this.tableauPizza.get(i).getCode().equals(codePizza)) {
				tableauPizza.set(i, pizza);
			}
		}

		ecriture();

	}

	@Override
	public void deletePizza(String codePizza) {

		for (int i = 0; i < this.tableauPizza.size(); i++) {
			if (this.tableauPizza.get(i).getCode().equals(codePizza)) {
				this.tableauPizza.remove(i);
			}
		}

		ecriture();

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {

		Pizza p = null;

		for (Pizza pizz : this.tableauPizza) {
			if (pizz.getCode().equals(codePizza)) {
				p = pizz;
			}
		}

		return p;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		boolean exist = false;
		for (int i = 0; i < this.tableauPizza.size(); i++) {
			if (this.tableauPizza.get(i).getCode().equals(codePizza)) {
				exist = true;
				break;
			}
		}
		return exist;
	}

	/**
	 * Ecrit dans un fichier .txt les données des pizzas
	 */
	public void ecriture() {

		List<String> listString = new ArrayList<>();

		for (Pizza pizza : tableauPizza)
				listString.add(pizza.toSave());

		gestionFichier.ecriture(listString);
	}

	/**
	 * Lis les données des pizzas dans un fichier .txt
	 */
	public void lecture() {

		List<String> listString = gestionFichier.lecture();

		CategoriePizza categorie = null;
		
		for (String s : listString) {
			String[] pizza = s.split(",");

			for (CategoriePizza z : CategoriePizza.values()) {
				if (z.getType().equals(pizza[4])) {
					categorie = z;
				}
			}
			this.tableauPizza.add(new Pizza(pizza[1], pizza[2], Double.parseDouble(pizza[3]), categorie));
		}

	}

	
	@Override
	public void initialiserBdd() {
		/*
		 * Utilisé uniquement avec PizzaBddDao
		 */
	}

	@Override
	public void destroyEmFactory() {
		/*
		 * Utilisé uniquement avec PizzaJpaDao
		 */
	}

}
