package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.pizzeria.exception.DataAccessException;
import fr.pizzeria.model.Pizza;

/**
 * Gestion du CRUD avec JPA (Java Persistence API)
 * 
 * @author Nicolas
 *
 */
public class PizzaJpaDao implements IPizzaDao {

//	private static EntityManagerFactory emFactory= Persistence.createEntityManagerFactory("pizzeria-console-objet");
	private static EntityManagerFactory emFactory= Persistence.createEntityManagerFactory("pizzeria-complete");

	private EntityManager openConnexion() {
		
		return emFactory.createEntityManager();

	}

	private void closeConnexion(EntityManager em) {

		em.close();

	}
	
	public void destroyEmFactory(){
		
		emFactory.close();
		
	}

	@Override
	public List<Pizza> findAllPizzas() {
		
		EntityManager em = this.openConnexion();
		EntityTransaction et = em.getTransaction();
		et.begin();
	
		TypedQuery<Pizza> requete = em.createQuery("select p from Pizza p", Pizza.class);
	
		List<Pizza> tabPizza = requete.getResultList();
	
		et.commit();
		this.closeConnexion(em);

		return tabPizza;

	}

	@Override
	public void saveNewPizza(Pizza pizza) {
			
		EntityManager em = this.openConnexion();
		EntityTransaction et = em.getTransaction();
		et.begin();

		em.persist(pizza);

		et.commit();
		this.closeConnexion(em);

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		
		if(this.pizzaExists(codePizza)) {

			Pizza pizza1 = findPizzaByCode(codePizza);
			
			EntityManager em = this.openConnexion();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			pizza1.setCode(pizza.getCode());
			pizza1.setLibelle(pizza.getLibelle());
			pizza1.setPrix(pizza.getPrix());
			pizza1.setCategorie(pizza.getCategorie());
				
			em.merge(pizza1);

			et.commit();
			this.closeConnexion(em);
				
		}
		else {
				
			throw new DataAccessException("Le code renseigné ne correspond à aucune pizza de la base de données");
				
		}
			

	}

	@Override
	public void deletePizza(String codePizza) {
		
		EntityManager em = this.openConnexion();
		EntityTransaction et = em.getTransaction();
		et.begin();	
		
		TypedQuery<Pizza> requete = em.createQuery("select p from Pizza p where p.code = :code", Pizza.class);
		requete.setParameter("code", codePizza);
			
		Pizza pizza = requete.getSingleResult();
		
		if (pizza != null){
			em.remove(pizza);
		}
		
		et.commit();
		this.closeConnexion(em);

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {

		EntityManager em = this.openConnexion();
		EntityTransaction et = em.getTransaction();
		et.begin();
	
		TypedQuery<Pizza> requete = em.createQuery("select p from Pizza p where p.code = :code", Pizza.class);
		requete.setParameter("code", codePizza);
			
		Pizza pizza = requete.getSingleResult();
			
		et.commit();
		this.closeConnexion(em);

		return pizza;

	}

	@Override
	public boolean pizzaExists(String codePizza) {
		
		boolean exist = false;
		
		List<Pizza> list = new ArrayList<>();
		
		list.add(findPizzaByCode(codePizza));
		
		if (list.size() == 1) {
			exist = true;
		}
		
		return exist;
	}

	
	@Override
	public void initialiserBdd() {
		/*
		 * Implémenté uniquement avec le PizzaBddDao
		 */
	}

}
