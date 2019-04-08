package fr.pizzeria.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzeriaMemDaoTest {
	
	private PizzaMemDao dao;
	
	@Before
	public void init() {
		dao = new PizzaMemDao();
	}
	
	/**
	 * Teste que l'on obtient bien une List de Pizza
	 */
	@Test
	public void testFindAllPizzas() {
		List tab = dao.findAllPizzas();
		Assert.assertTrue(tab.get(0) instanceof Pizza);
	}
	
	/**
	 * Teste que la méthode saveNewPizza ajoute bien l'objet pizza voulu à la List
	 */
	@Test
	public void testSaveNewPizza() {
		int tabSize = dao.getTableauPizza().size();
		dao.saveNewPizza(new Pizza());
		Assert.assertTrue((tabSize + 1) == dao.getTableauPizza().size());
	}
	
	/**
	 * Teste que la méthode updatePizza met bien à jour les données de la pizza dans la liste
	 */
	@Test
	public void testUpdatePizza() {
		dao.saveNewPizza(new Pizza(9, "CDE", "PizzaBidon", 15.0, CategoriePizza.VIANDE));
		dao.updatePizza("CDE", new Pizza(8, "CDA", "Bidon", 1.0, CategoriePizza.POISSON));
		Pizza pizza = dao.findPizzaByCode("CDA");
		
		Assert.assertTrue(pizza.getId() == 8);
		Assert.assertTrue(pizza.getCode() == "CDA");
		Assert.assertTrue(pizza.getLibelle() == "Bidon");
		Assert.assertTrue(pizza.getPrix() == 1.0);
		Assert.assertTrue(pizza.getCategorie() == CategoriePizza.POISSON);
	}
	
	/**
	 * Teste que la méthode deletePizza supprime bien la pizza voulue dans la liste
	 */
	@Test
	public void testdeletePizza() {
		dao.saveNewPizza(new Pizza(9, "CDE", "PizzaBidon", 15.0, CategoriePizza.VIANDE));
		dao.deletePizza("CDE");
		Assert.assertFalse(dao.pizzaExists("CDE"));
	}
	
	/**
	 * Teste que la méthode findPizzaByCode récupère bienla bonne pizza
	 */
	@Test
	public void testfindPizzaByCode() {
		dao.saveNewPizza(new Pizza(9, "CDE", "PizzaBidon", 15.0, CategoriePizza.VIANDE));
		Pizza pizza = dao.findPizzaByCode("CDE");
		Assert.assertNotNull(pizza);
		Assert.assertTrue(pizza.getCode() == "CDE");
	}
	
	/**
	 * Teste que la méthode pizzaExist renvoie bien true
	 */
	@Test
	public void testPizzaExist() {
		Assert.assertFalse(dao.pizzaExists("CDE"));
		Pizza pizza = new Pizza(9, "CDE", "PizzaBidon", 15.0, CategoriePizza.VIANDE);
		dao.saveNewPizza(pizza);
		Assert.assertTrue(pizza.getCode() == "CDE");
	}

}
