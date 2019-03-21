package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

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

	public PizzaFileDao() {
		// Initialisation de l'ArrayList
		this.tableauPizza = new ArrayList<>();

		// Données Initiale (A commenter après l'initialisation du fichier .txt)
//		initialisation();
//		ecriture();

		// Initialisation du fichier save.txt
		gestionFichier = new GestionFichier();
		lecture();
	}

	/**
	 * Initialise les données de base de la liste de pizzas
	 */
	public void initialisation() {
		tableauPizza.add(new Pizza(0, "PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(1, "MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(2, "REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(3, "FRO", "Les 4 fromages", 12.00, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(4, "CAN", "La canibale", 12.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(5, "SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(6, "ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(7, "IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
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

		List<String> listString = new ArrayList<String>();

		for (Pizza pizza : tableauPizza)
			if (pizza != null)
				listString.add(pizza.toSave());

		gestionFichier.ecriture(listString);
	}

	/**
	 * Lis les données des pizzas dans un fichier .txt
	 */
	public void lecture() {

		List<String> listString = gestionFichier.lecture();

		int max = 0;
		CategoriePizza categorie = null;
		
		for (String s : listString) {
			String[] pizza = s.split(",");
			max = Integer.parseInt(pizza[0]);
			for (CategoriePizza z : CategoriePizza.values()) {
				if (z.getType().equals(pizza[4])) {
					categorie = z;
				}
			}
			this.tableauPizza.add(new Pizza(Integer.parseInt(pizza[0]), pizza[1], pizza[2], Double.parseDouble(pizza[3]), categorie));
		}
		Pizza.setCompteur(max);
	}

}
