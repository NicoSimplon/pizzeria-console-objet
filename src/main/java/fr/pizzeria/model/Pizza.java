package fr.pizzeria.model;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import fr.pizzeria.utils.*;

/**
 * Classe représentatnt une pizza
 * 
 * @author Nicolas
 *
 */
@Entity
@Table(name = "pizzas")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ToString(upperCase = true)
	@Column(name = "code")
	private String code;

	@ToString(separateur = " -> ", upperCase = true)
	@Column(name = "libelle")
	private String libelle;

	@ToString(separateur = " - ")
	@Column(name = "prix")
	private double prix;

	private static int compteur = 0;

	@ToString(separateur = " - ")
	@Enumerated(EnumType.STRING)
	private CategoriePizza categorie;

	/**
	 * Constructeur par défaut
	 */
	public Pizza() {
		
	}

	/**
	 * Constructeur avec compteur
	 * 
	 * @param code
	 *            Référence de la pizza
	 * @param libelle
	 *            Nom de la pizza
	 * @param prix
	 *            Prix de la pizza
	 */
	public Pizza(String code, String libelle, double prix, CategoriePizza categorie) {
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.categorie = categorie;
		this.id = Pizza.compteur;
		Pizza.compteur += 1;
	}

	/**
	 * Constructeur avec un id renseigné à la création
	 * 
	 * @param id
	 *            Identifiant de la pizza
	 * @param code
	 *            Référence de la pizza
	 * @param libelle
	 *            Nom de la pizza
	 * @param prix
	 *            Prix de la pizza
	 */
	public Pizza(int id, String code, String libelle, double prix, CategoriePizza categorie) {
		this(code, libelle, prix, categorie);
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		// return this.code + "->" + this.libelle + " (" + this.prix + " €)" + "
		// catégorie: " + this.categorie;

		Class<? extends Pizza> classe = getClass();
		Field[] fields = classe.getDeclaredFields();
		String retour = "";

		for (Field f : fields) {
			if (f.isAnnotationPresent(ToString.class)) {
				try {
					ToString annotation = f.getAnnotation(ToString.class);
					boolean uppercase = annotation.upperCase();
					String separateur = annotation.separateur();

					retour += separateur;

					if (uppercase == true)
						retour += f.get(this).toString().toUpperCase();
					else
						retour += f.get(this);

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		return retour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prix);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (Double.doubleToLongBits(prix) != Double.doubleToLongBits(other.prix))
			return false;
		return true;
	}

	/**
	 * Met en forme les données relatives aux pizzas en vue de leur écriture
	 * dans un fichier .txt
	 * 
	 * @return String
	 */
	public String toSave() {
		return (id + "," + code + "," + libelle + "," + prix + "," + categorie);
	}

	/**
	 * @return int Retourne le paramètre id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            Identifiant de la pizza
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return String Retourne l'id de la pizza
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code
	 *            Référence de la pizza
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return String Retourne le nom de la pizza
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle
	 *            Nom de la pizza
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return double Retourne le prix de la pizza
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix
	 *            Prix de la pizza
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * @return int Nomnre de pizzas
	 */
	public static int getCompteur() {
		return compteur;
	}

	/**
	 * @param compteur
	 */
	public static void setCompteur(int compteur) {
		Pizza.compteur = compteur;
	}

	public CategoriePizza getCategorie() {
		return this.categorie;
	}

	public void setCategorie(CategoriePizza categoriePizza) {
		this.categorie = categoriePizza;
	}

}
