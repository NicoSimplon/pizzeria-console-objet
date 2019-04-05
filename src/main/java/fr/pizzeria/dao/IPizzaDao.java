package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.model.Pizza;

/**
 * Interface Gestion des données relatives à une pizza
 * 
 * @author Nicolas
 *
 */
public interface IPizzaDao {
	
	/**
	 * @return Retourne un tableau contenant tous les objets pizzas existants
	 */
	List<Pizza> findAllPizzas();
	
	/**
	 * Ajoute une nouvelle pizza
	 * 
	 * @param pizza objet de la nouvelle pizza à ajouter dans le tableau
	 */
	void saveNewPizza(Pizza pizza);
	
	/**
	 * Modifie les propriétés de l'ojet pizza
	 * 
	 * @param codePizza code de la pizza à modifier
	 * @param pizza objet pizza modifié
	 */
	void updatePizza(String codePizza, Pizza pizza);
	
	/**
	 * @param codePizza Code de la pizza à supprimer
	 */
	void deletePizza(String codePizza);
	
	/**
	 * @param codePizza Référence de la pizza souhaité
	 * @return Retourne l'objet pizza souhaité
	 */
	Pizza findPizzaByCode(String codePizza);
	
	/**
	 * @param codePizza Référence de la pizza souhaité
	 * @return true si la pizza existe déjà sinon false
	 */
	boolean pizzaExists(String codePizza);

	/**
	 * Ajoute les pizzas de base dans la base de données (elle doit exister et contenie la table pizzas)
	 */
	void initialiserBdd();

	/**
	 * Cloture du EntityManagerFactory (uniquement lorsque le PizzaJpaDao est actif)
	 */
	void destroyEmFactory();

}