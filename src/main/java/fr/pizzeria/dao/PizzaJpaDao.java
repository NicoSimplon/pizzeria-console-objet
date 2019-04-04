package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.pizzeria.exception.DataAccessException;
import fr.pizzeria.model.Pizza;

/**
 * Gestion du CRUD avec JPA (Java Persist)
 * 
 * @author Nicolas
 *
 */
public class PizzaJpaDao implements IPizzaDao {

	private EntityManagerFactory emFactory;
	private EntityManager em;
	private EntityTransaction et;

	private void openConnexion() {

		this.emFactory = Persistence.createEntityManagerFactory("pizzeria-console-objet");
		this.em = this.emFactory.createEntityManager();
		this.et = this.em.getTransaction();

	}

	private void closeConnexion() {

		this.em.close();
		this.emFactory.close();

	}

	@Override
	public List<Pizza> findAllPizzas() {
		
		this.openConnexion();
		this.et.begin();
	
		TypedQuery<Pizza> requete = this.em.createQuery("select p from Pizza p", Pizza.class);
	
		List<Pizza> tabPizza = requete.getResultList();
	
		this.et.commit();
		this.closeConnexion();

		return tabPizza;

	}

	@Override
	public void saveNewPizza(Pizza pizza) {
			
		this.openConnexion();
		this.et.begin();

		this.em.persist(pizza);

		this.et.commit();

		this.closeConnexion();

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		
		this.openConnexion();
		this.et.begin();
			
		if(this.pizzaExists(codePizza)) {
				
			Pizza pizza1 = findPizzaByCode(codePizza);
				
			pizza1.setCode(pizza.getCode());
			pizza1.setLibelle(pizza.getLibelle());
			pizza1.setPrix(pizza.getPrix());
			pizza1.setCategorie(pizza.getCategorie());
				
			this.em.merge(pizza1);
			this.et.commit();
				
		}
		else {
				
			throw new DataAccessException("Le code renseigné ne correspond à aucune pizza de la base de données");
				
		}
			
		this.closeConnexion();

	}

	@Override
	public void deletePizza(String codePizza) {
		
		this.openConnexion();
		this.et.begin();
			
		Pizza pizza = findPizzaByCode(codePizza);
			
		this.em.remove(pizza);
	
		this.et.commit();	
		this.closeConnexion();

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {

		this.openConnexion();
		this.et.begin();
	
		TypedQuery<Pizza> requete = this.em.createQuery("select p from Pizza p where p.code = :code", Pizza.class);
		requete.setParameter("code", codePizza);
			
		Pizza pizza = requete.getSingleResult();
			
		this.et.commit();
		this.closeConnexion();

		return pizza;

	}

	@Override
	public boolean pizzaExists(String codePizza) {
		
		if(findPizzaByCode(codePizza) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Ne fonctionne qu'avec le PizzaBddDao
	 */
	@Override
	public void initialiserBdd() {
		

	}

}
