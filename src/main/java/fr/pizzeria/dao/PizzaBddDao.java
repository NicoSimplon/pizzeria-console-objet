package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	private void openConnexion() {

		try {

			Class.forName(this.driver);

			connexionBDD = DriverManager.getConnection(this.url, this.user, this.password);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	private void closeConnexion() {
		try {

			st.close();
			connexionBDD.close();

		} catch (SQLException e) {

			e.printStackTrace();

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

			closeConnexion();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
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

			closeConnexion();

		} catch (SQLException e) {

			e.printStackTrace();

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

			closeConnexion();

		} catch (SQLException e) {

			e.printStackTrace();

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

			closeConnexion();

		} catch (SQLException e) {

			e.printStackTrace();

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
			closeConnexion();

			return piz;

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return null;
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
			closeConnexion();

			if (rs != null) {

				return true;

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return false;
	}

}
