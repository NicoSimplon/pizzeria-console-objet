package fr.pizzeria.menu;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class MenuFactoryTest {
	
	private MenuFactory mf;
	
	@Before
	public void init() {
		mf = new MenuFactory();
	}

	@Test
	public void testListerPizzaService() {

		MenuService ms = mf.create("lister");
		
		Assert.assertTrue(ms instanceof ListerPizzasService);
		
	}
	
	@Test
	public void testAjouterPizzaService() {

		MenuService ms = mf.create("ajouter");
		
		Assert.assertTrue(ms instanceof AjouterPizzaService);
		
	}
	
	@Test
	public void testModifierPizzaService() {

		MenuService ms = mf.create("modifier");
		
		Assert.assertTrue(ms instanceof ModifierPizzaService);
		
	}
	
	@Test
	public void testSupprimerPizzaService() {

		MenuService ms = mf.create("supprimer");
		
		Assert.assertTrue(ms instanceof SupprimerPizzaService);
		
	}
	
	@Test
	public void testFermetureService() {

		MenuService ms = mf.create("fermeture");
		
		Assert.assertTrue(ms instanceof FermetureService);
		
	}
	
	

}
