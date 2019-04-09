package fr.pizzeria.menu;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuFactoryTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(MenuFactory.class);
	
	private MenuFactory mf;
	
	@Before
	public void init() {
		LOG.info("Etant donné une instance de MenuFactory");
		mf = new MenuFactory();
	}

	@Test
	public void testListerPizzaService() {

		LOG.info("Lorsque la méthode create est appelée avec la clé lister");
		MenuService ms = mf.create("lister");
		
		LOG.info("Alors on obtient une instance de ListerPizzasService");
		Assert.assertTrue(ms instanceof ListerPizzasService);
		
	}
	
	@Test
	public void testAjouterPizzaService() {

		LOG.info("Lorsque la méthode create est appelée avec la clé ajouter");
		MenuService ms = mf.create("ajouter");
		
		LOG.info("Alors on obtient une instance de AjouterPizzaService");
		Assert.assertTrue(ms instanceof AjouterPizzaService);
		
	}
	
	@Test
	public void testModifierPizzaService() {

		LOG.info("Lorsque la méthode create est appelée avec la clé modifier");
		MenuService ms = mf.create("modifier");
		
		LOG.info("Alors on obtient une instance de ModifierPizzaService");
		Assert.assertTrue(ms instanceof ModifierPizzaService);
		
	}
	
	@Test
	public void testSupprimerPizzaService() {

		LOG.info("Lorsque la méthode create est appelée avec la clé supprimer");
		MenuService ms = mf.create("supprimer");
		
		LOG.info("Alors on obtient une instance de SupprimerPizzaService");
		Assert.assertTrue(ms instanceof SupprimerPizzaService);
		
	}
	
	@Test
	public void testFermetureService() {

		LOG.info("Lorsque la méthode create est appelée avec la clé fermeture");
		MenuService ms = mf.create("fermeture");
		
		LOG.info("Alors on obtient une instance de FermetureService");
		Assert.assertTrue(ms instanceof FermetureService);
		
	}
	
	

}
