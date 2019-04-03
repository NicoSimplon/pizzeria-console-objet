package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	/**
	 * Récupère la liste des pizzas sous forme d'objets de type Pizza
	 */
	@Override
	public List<Pizza> findAllPizzas() {

		List<Pizza> tabPizz = new ArrayList<>();

		try {
			
			Class.forName("com.mysql.jdbc.Driver");

			String jdbcUrl = "jdbc:mysql://localhost:3306/bdd_pizzeria?useSSL=false";

			Connection uneConnexion = DriverManager.getConnection(jdbcUrl, "root", "");

			PreparedStatement st = uneConnexion.prepareStatement ("SELECT * FROM pizzas;");
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

			st.close();

			uneConnexion.close();

			

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			System.out.println (e.getMessage());
		}

		return tabPizz;

	}

	/**
	 * Ajoute une pizza dans la base de données
	 */
	@Override
	public void saveNewPizza(Pizza pizza) {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			String jdbcUrl = "jdbc:mysql://localhost:3306/bdd_pizzeria?useSSL=false";

			Connection uneConnexion = DriverManager.getConnection(jdbcUrl, "root", "");
			
			PreparedStatement insertPizza = uneConnexion.prepareStatement(
				"INSERT INTO pizzas (code, libelle, prix, categorie) VALUES(?, ?, ?, ?);"
			);
			insertPizza.setString(1, pizza.getCode());
			insertPizza.setString(2, pizza.getLibelle());
			insertPizza.setDouble(3, pizza.getPrix());
			insertPizza.setString(4, pizza.getCategorie().getType());
			insertPizza.executeUpdate();


			insertPizza.close();
			uneConnexion.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

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

			Class.forName("com.mysql.jdbc.Driver");

			String jdbcUrl = "jdbc:mysql://localhost:3306/bdd_pizzeria?useSSL=false";

			Connection uneConnexion = DriverManager.getConnection(jdbcUrl, "root", "");
			
			PreparedStatement updatePizza = uneConnexion.prepareStatement(
				"UPDATE pizzas SET code = ?, libelle = ?, prix = ?, categorie = ? WHERE code = ?;"
			);
			updatePizza.setString(1, pizza.getCode());
			updatePizza.setString(2, pizza.getLibelle());
			updatePizza.setDouble(3, pizza.getPrix());
			updatePizza.setString(4, pizza.getCategorie().getType());
			updatePizza.setString(5, codePizza);
			updatePizza.executeUpdate();


			updatePizza.close();
			uneConnexion.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

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

			Class.forName("com.mysql.jdbc.Driver");

			String jdbcUrl = "jdbc:mysql://localhost:3306/bdd_pizzeria?useSSL=false";

			Connection uneConnexion = DriverManager.getConnection(jdbcUrl, "root", "");
			
			PreparedStatement deletePizza = uneConnexion.prepareStatement(
				"DELETE FROM pizzas WHERE code = ?;"
			);

			deletePizza.setString(1, codePizza);
			deletePizza.executeUpdate();


			deletePizza.close();
			uneConnexion.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

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

			Class.forName("com.mysql.jdbc.Driver");

			String jdbcUrl = "jdbc:mysql://localhost:3306/bdd_pizzeria?useSSL=false";

			Connection uneConnexion = DriverManager.getConnection(jdbcUrl, "root", "");
			
			PreparedStatement selectPizza = uneConnexion.prepareStatement(
				"SELECT * FROM pizzas WHERE code = ?;"
			);

			selectPizza.setString(1, codePizza);
			ResultSet rs = selectPizza.executeQuery();
			
			int id = rs.getInt("id");
			String code = rs.getString("code");
			String libelle = rs.getString("libelle");
			double prix = rs.getDouble("prix");
			String categorie = rs.getString("categorie");
			
			CategoriePizza categoriePizza = CategoriePizza.valueOf(categorie);

			Pizza piz = new Pizza(id, code, libelle, prix, categoriePizza);
			
			rs.close();
			selectPizza.close();
			uneConnexion.close();

			return piz;

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

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

			Class.forName("com.mysql.jdbc.Driver");

			String jdbcUrl = "jdbc:mysql://localhost:3306/bdd_pizzeria?useSSL=false";

			Connection uneConnexion = DriverManager.getConnection(jdbcUrl, "root", "");
			
			PreparedStatement selectPizza = uneConnexion.prepareStatement(
				"SELECT * FROM pizzas WHERE code = ?;"
			);

			selectPizza.setString(1, codePizza);
			ResultSet rs = selectPizza.executeQuery();
			
			rs.close();
			selectPizza.close();
			uneConnexion.close();

			if (rs != null){
				
				return true;

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		
		return false;
	}

}
