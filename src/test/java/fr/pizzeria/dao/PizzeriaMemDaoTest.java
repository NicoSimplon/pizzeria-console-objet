package fr.pizzeria.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzeriaMemDaoTest {
	
	private PizzaMemDao dao;
	
	private static final Logger LOG = LoggerFactory.getLogger(PizzaMemDao.class);
	
	@Before
	public void init() {
		LOG.info("Etant donné une instance de PizzaMemDao");
		dao = new PizzaMemDao();
	}
	
	/**
	 * Teste que l'on obtient bien une List de Pizza
	 */
	@Test
	public void testFindAllPizzas() {
		/*
		 * Etant donné ...
		 * Lorsque ...
		 * Alors ...
		 */
		
		LOG.info("Lorsqu'on recherche la liste de pizzas");
		List<Pizza> tab = dao.findAllPizzas();
		
		LOG.info("Alors la liste de pizzas contient au moins une pizza");
		Assert.assertFalse(tab.isEmpty());
	}
	
	/**
	 * Teste que la méthode saveNewPizza ajoute bien l'objet pizza voulu à la List
	 */
	@Test
	public void testSaveNewPizza() {
		/*
		 * Etant donné ...
		 * Lorsque ...
		 * Alors ...
		 */
		
		LOG.info("Lorsqu'on recherche la taille de la liste de pizzas");
		int tabSize = dao.getTableauPizza().size();
		
		LOG.info("Lorsqu'on ajoute une nouvelle pizza à la liste");
		dao.saveNewPizza(new Pizza(9, "CDE", "PizzaBidon", 15.0, CategoriePizza.VIANDE));
		
		LOG.info("Alors la taille de la liste augmente de 1");
		Assert.assertTrue((tabSize + 1) == dao.getTableauPizza().size());
		
		LOG.info("Alors la nouvelle pizza existe dans la liste");
		Assert.assertTrue(dao.pizzaExists("CDE"));
	}
	
	/**
	 * Teste que la méthode updatePizza met bien à jour les données de la pizza dans la liste
	 */
	@Test
	public void testUpdatePizza() {
		/*
		 * Etant donné ...
		 * Lorsque ...
		 * Alors ...
		 */
		
		LOG.info("Lorsqu'on ajoute une nouvelle pizza à la liste");
		dao.saveNewPizza(new Pizza(9, "CDE", "PizzaBidon", 15.0, CategoriePizza.VIANDE));
		
		LOG.info("Lorsqu'on modifie la pizza nouvellement ajoutée");
		dao.updatePizza("CDE", new Pizza(8, "CDA", "Bidon", 1.0, CategoriePizza.POISSON));
		
		LOG.info("Lorsqu'on récupère cette pizza");
		Pizza pizza = dao.findPizzaByCode("CDA");
		
		LOG.info("Alors l'id a bien été modifié");
		Assert.assertTrue(pizza.getId() == 8);
		
		LOG.info("Alors le code a bien été modifié");
		Assert.assertTrue(pizza.getCode().equals("CDA"));
		
		LOG.info("Alors le libellé a bien été modifié");
		Assert.assertTrue(pizza.getLibelle().equals("Bidon"));
		
		LOG.info("Alors le prix a bien été modifié");
		Assert.assertTrue(pizza.getPrix() == 1.0);
		
		LOG.info("Alors la catégorie a bien été modifié");
		Assert.assertTrue(pizza.getCategorie() == CategoriePizza.POISSON);
	}
	
	/**
	 * Teste que la méthode deletePizza supprime bien la pizza voulue dans la liste
	 */
	@Test
	public void testdeletePizza() {
		/*
		 * Etant donné ...
		 * Lorsque ...
		 * Alors ...
		 */
		
		LOG.info("Lorsqu'on ajoute une nouvelle pizza à la liste");
		dao.saveNewPizza(new Pizza(9, "CDE", "PizzaBidon", 15.0, CategoriePizza.VIANDE));
		
		LOG.info("Lorsqu'on supprime cette pizza de la liste");
		dao.deletePizza("CDE");
		
		LOG.info("Alors cette pizza n'existe plus danns la liste");
		Assert.assertFalse(dao.pizzaExists("CDE"));
	}
	
	/**
	 * Teste que la méthode findPizzaByCode récupère bienla bonne pizza
	 */
	@Test
	public void testfindPizzaByCode() {
		/*
		 * Etant donné ...
		 * Lorsque ...
		 * Alors ...
		 */
		
		LOG.info("Lorsqu'on ajoute une nouvelle pizza à la liste");
		dao.saveNewPizza(new Pizza(9, "CDE", "PizzaBidon", 15.0, CategoriePizza.VIANDE));
		
		LOG.info("Lorsqu'on recherche cette pizza");
		Pizza pizza = dao.findPizzaByCode("CDE");
		
		LOG.info("Alors cette existe dans la liste");
		Assert.assertTrue(dao.pizzaExists("CDE"));
		
		LOG.info("Alors elle a bien le même code");
		Assert.assertTrue(pizza.getCode() == "CDE");
	}
	
	/**
	 * Teste que la méthode pizzaExist renvoie bien true
	 */
	@Test
	public void testPizzaExist() {
		/*
		 * Etant donné ...
		 * Lorsque ...
		 * Alors ...
		 */
		
		LOG.info("Etant donné que la pizza à tester n'existe pas dans la liste");
		Assert.assertFalse(dao.pizzaExists("CDE"));
		
		LOG.info("Lorsqu'on ajoute la pizza à tester dans la liste");
		dao.saveNewPizza(new Pizza(9, "CDE", "PizzaBidon", 15.0, CategoriePizza.VIANDE));
		
		LOG.info("Alors elle existe bien dans la liste");
		Assert.assertTrue(dao.pizzaExists("CDE"));
	}

}
