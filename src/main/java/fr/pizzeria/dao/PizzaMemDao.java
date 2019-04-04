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
public class PizzaMemDao implements IPizzaDao {

	private List<Pizza> tableauPizza;

	public PizzaMemDao() {
		List<Pizza> tableauPizza = new ArrayList<>();
		tableauPizza.add(new Pizza(0, "PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(1, "MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(2, "REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(3, "FRO", "Les 4 fromages", 12.00, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(4, "CAN", "La canibale", 12.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(5, "SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(6, "ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(7, "IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
		
		this.tableauPizza = tableauPizza;
	}

	@Override
	public List<Pizza> findAllPizzas() {
		return this.tableauPizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {

		this.tableauPizza.add(pizza);

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {

		for (int i = 0; i < this.tableauPizza.size(); i++) {
			if (this.tableauPizza.get(i).getCode().equals(codePizza)) {
				tableauPizza.set(i, pizza);
			}
		}

	}

	@Override
	public void deletePizza(String codePizza) {

		for (int i = 0; i < this.tableauPizza.size(); i++) {
			if (this.tableauPizza.get(i).getCode().equals(codePizza)) {
				this.tableauPizza.remove(i);
			}
		}

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {

		Pizza p = null;

		for (Pizza piz : this.tableauPizza) {
			if (piz.getCode().equals(codePizza)) {
				p = piz;
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
	 * Utilisé uniquement avec PizzaBddDao
	 */
	@Override
	public void initialiserBdd() {
		
	}

}
