package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

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

	String jdbcUrl = null;
	String userName = null;
	String password = null;
	Connection connexionBDD = null;
	PreparedStatement st = null;
	
	private void beginConnexionBdd ()
	{
		try
		{
			
			GestionFichier file = new GestionFichier ("jdbc.properties");

			List <String> listString = file.lecture();
			

			jdbcUrl = listString.get(0).split(";")[1];
			userName = listString.get(1).split(";")[1];
			password = listString.get(2).split(";")[1];
			
			Class.forName("com.mysql.jdbc.Driver");
			
			connexionBDD = DriverManager.getConnection(jdbcUrl, userName, password);
			//connexionBDD.setAutoCommit(false);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private void closeConnexionBdd()
	{
		try
		{
			st.close ();
			connexionBDD.close ();
		}
		catch (SQLException e)
		{
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
			
			beginConnexionBdd();

			st = connexionBDD.prepareStatement ("SELECT * FROM pizzas;");
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

			closeConnexionBdd();

			

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

			beginConnexionBdd();
			
			st = connexionBDD.prepareStatement(
				"INSERT INTO pizzas (code, libelle, prix, categorie) VALUES(?, ?, ?, ?);"
			);
			st.setString(1, pizza.getCode());
			st.setString(2, pizza.getLibelle());
			st.setDouble(3, pizza.getPrix());
			st.setString(4, pizza.getCategorie().getType());
			st.executeUpdate();

			closeConnexionBdd();
			

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

			beginConnexionBdd();
			
			st = connexionBDD.prepareStatement(
				"UPDATE pizzas SET code = ?, libelle = ?, prix = ?, categorie = ? WHERE code = ?;"
			);
			st.setString(1, pizza.getCode());
			st.setString(2, pizza.getLibelle());
			st.setDouble(3, pizza.getPrix());
			st.setString(4, pizza.getCategorie().getType());
			st.setString(5, codePizza);
			st.executeUpdate();

			closeConnexionBdd();


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

			beginConnexionBdd();
			
			st = connexionBDD.prepareStatement(
				"DELETE FROM pizzas WHERE code = ?;"
			);

			st.setString(1, codePizza);
			st.executeUpdate();


			closeConnexionBdd();

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

			beginConnexionBdd();
			
			st = connexionBDD.prepareStatement(
				"SELECT * FROM pizzas WHERE code = ?;"
			);

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
			closeConnexionBdd();

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

			beginConnexionBdd();
			
			st = connexionBDD.prepareStatement(
				"SELECT * FROM pizzas WHERE code = ?;"
			);

			st.setString(1, codePizza);
			ResultSet rs = st.executeQuery();
			
			rs.close();
			closeConnexionBdd();

			if (rs != null){
				
				return true;

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}
		
		return false;
	}

}
