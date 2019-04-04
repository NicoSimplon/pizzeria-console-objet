package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.pizzeria.exception.DataAccessException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Gestion du CRUD en interaction avec une base de données
 * 
 * 
 * @author Nicolas
 *
 */
public class PizzaBddDao implements IPizzaDao {

	private String driver;
	private String url;
	private String user;
	private String password;
	Connection connexionBDD = null;
	PreparedStatement st = null;

	/**
	 * Constructeur avec initialisation des variables de connexion à la BDD
	 */
	public PizzaBddDao() {

		super();
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		this.url = bundle.getString("jdbc.url");
		this.user = bundle.getString("jdbc.user");
		this.password = bundle.getString("jdbc.password");
		this.driver = bundle.getString("jdbc.driver");

	}

	/**
	 * Ouvre une connexion à la base de données
	 */
	private void openConnexion() {

		try {

			Class.forName(this.driver);

			connexionBDD = DriverManager.getConnection(this.url, this.user, this.password);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			throw new DataAccessException ("La connexion à la base de données ne s'est pas effectuée correctement", e);

		}
	}

	/**
	 * Ferme la connexion à la base de données
	 */
	private void closeConnexion() {
		try {

			st.close();
			connexionBDD.close();

		} catch (SQLException e) {

			throw new DataAccessException ("La clôture de la connexion à la base de données ne s'est pas déroulée correctement", e);

		}
	}

	/**
	 * Récupère la liste des pizzas sous forme d'objets de type Pizza
	 */
	@Override
	public List<Pizza> findAllPizzas() {

		List<Pizza> tabPizz = new ArrayList<>();

		try {

			openConnexion();

			st = connexionBDD.prepareStatement("SELECT * FROM pizzas;");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");
				String code = rs.getString("code");
				String libelle = rs.getString("libelle");
				double prix = rs.getDouble("prix");
				String categorie = rs.getString("categorie");

				CategoriePizza categoriePizza = CategoriePizza.valueOf(categorie.toUpperCase());

				Pizza piz = new Pizza(id, code, libelle, prix, categoriePizza);

				tabPizz.add(piz);
			}

			rs.close();

		} 
		catch (SQLException e) {
			

			throw new DataAccessException ("Une erreur est survenue durant la recherche de la liste des pizzas", e);
			
		} 
		finally {
			
			closeConnexion();
			
		}

		return tabPizz;

	}

	/**
	 * Ajoute une pizza dans la base de données
	 */
	@Override
	public void saveNewPizza(Pizza pizza) {

		try {

			openConnexion();

			st = connexionBDD
					.prepareStatement("INSERT INTO pizzas (code, libelle, prix, categorie) VALUES(?, ?, ?, ?);");
			st.setString(1, pizza.getCode());
			st.setString(2, pizza.getLibelle());
			st.setDouble(3, pizza.getPrix());
			st.setString(4, pizza.getCategorie().getType());
			st.executeUpdate();

		}
		catch (SQLException e) {

			throw new DataAccessException ("L'ajout de la nouvelle pizza ne s'est pas déroulé correctement", e);

		}
		finally {
			
			closeConnexion();
			
		}

	}

	/**
	 * Met à jour les données d'une pizza dans la base de données
	 */
	@Override
	public void updatePizza(String codePizza, Pizza pizza) {

		try {

			openConnexion();

			st = connexionBDD.prepareStatement(
					"UPDATE pizzas SET code = ?, libelle = ?, prix = ?, categorie = ? WHERE code = ?;");

			st.setString(1, pizza.getCode());
			st.setString(2, pizza.getLibelle());
			st.setDouble(3, pizza.getPrix());
			st.setString(4, pizza.getCategorie().getType());
			st.setString(5, codePizza);
			st.executeUpdate();

		}
		catch (SQLException e) {

			throw new DataAccessException ("La modification de la pizza demandée ne s'est pas déroulée correctement", e);

		}
		finally {
			
			closeConnexion();
			
		}

	}

	/**
	 * Supprime une pizza dans le base de données
	 */
	@Override
	public void deletePizza(String codePizza) {

		try {

			openConnexion();

			st = connexionBDD.prepareStatement("DELETE FROM pizzas WHERE code = ?;");

			st.setString(1, codePizza);
			st.executeUpdate();

		}
		catch (SQLException e) {

			throw new DataAccessException ("La suppression de la pizza demandée ne s'est pas déroulée correctement", e);

		}
		finally {
			
			closeConnexion();
			
		}

	}

	/**
	 * Récupère les données d'une pizza via son code
	 */
	@Override
	public Pizza findPizzaByCode(String codePizza) {

		try {

			openConnexion();

			st = connexionBDD.prepareStatement("SELECT * FROM pizzas WHERE code = ?;");

			st.setString(1, codePizza);
			ResultSet rs = st.executeQuery();

			int id = rs.getInt("id");
			String code = rs.getString("code");
			String libelle = rs.getString("libelle");
			double prix = rs.getDouble("prix");
			String categorie = rs.getString("categorie");

			CategoriePizza categoriePizza = CategoriePizza.valueOf(categorie.toUpperCase());

			Pizza piz = new Pizza(id, code, libelle, prix, categoriePizza);

			rs.close();

			return piz;

		} 
		catch (SQLException e) {

			throw new DataAccessException ("Une erreur est survenue durant la recherche de votre pizza", e);

		}
		finally {
			
			closeConnexion();
			
		}
	}

	/**
	 * Vérifie si une pizza portant le même code existe déjà
	 */
	@Override
	public boolean pizzaExists(String codePizza) {

		try {

			openConnexion();

			st = connexionBDD.prepareStatement("SELECT * FROM pizzas WHERE code = ?;");

			st.setString(1, codePizza);
			ResultSet rs = st.executeQuery();

			rs.close();

			if (rs.getFetchSize() > 0) {

				return true;

			}

		} 
		catch (SQLException e) {

			throw new DataAccessException ("Une erreur est survenue durant la recherche de votre pizza", e);

		}
		finally {
			
			closeConnexion();
			
		}

		return false;
	}
	
	public void initialiserBdd() {
		
		// Initialisation de la liste avec les pizzas de base
		List<Pizza> tableauPizza = new ArrayList<>();
		tableauPizza.add(new Pizza(0, "PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(1, "MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(2, "REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(3, "FRO", "Les 4 fromages", 12.00, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(4, "CAN", "La canibale", 12.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(5, "SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(6, "ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		tableauPizza.add(new Pizza(7, "IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
		
		try {

			openConnexion();
			
			for (Pizza p : tableauPizza) {
				
				st = connexionBDD.prepareStatement("INSERT INTO pizzas (code, libelle, prix, categorie) VALUES(?, ?, ?, ?);");
				
				st.setString(1, p.getCode());
				st.setString(2, p.getLibelle());
				st.setDouble(3, p.getPrix());
				st.setString(4, p.getCategorie().getType());
				st.executeUpdate();
				
				ResultSet rs = st.executeQuery();

			}

		} 
		catch (SQLException e) {

			throw new DataAccessException (
				"Une erreur est survenue durant l'initialisation de la base de données,"
				+ " il est possible q'une ou plusieurs pizza existent déjà", 
			e);

		}
		finally {
			
			closeConnexion();
			
		}
		
	}

}
