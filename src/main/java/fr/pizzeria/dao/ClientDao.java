package fr.pizzeria.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.pizzeria.model.Client;

public class ClientDao {

	private static EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("pizzeria-complete");

	private EntityManager openConnexion() {

		return emFactory.createEntityManager();

	}

	private void closeConnexion(EntityManager em) {

		em.close();

	}

	public void destroyEmFactory() {

		emFactory.close();

	}

	public void saveNewClient(Client client) {

		EntityManager em = openConnexion();
		EntityTransaction et = em.getTransaction();

		if(this.emailDontExist(client.getEmail())) {
			et.begin();			
			em.persist(client);
			et.commit();	
		}

		closeConnexion(em);
		
	}

	public boolean isValid(String email, String password) {

		boolean validation = false;

		return validation;
	}
	
	public boolean emailDontExist(String amail) {
		boolean dontExist = false;

		return dontExist;
	}

}
