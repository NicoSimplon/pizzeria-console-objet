package fr.pizzeria.model;

/**
 * Classe représentatnt une pizza
 * 
 * @author Nicolas
 *
 */
public class Pizza {

	private int id;
	private String code;
	private String libelle;
	private double prix;
	private static int compteur;

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
	public Pizza(String code, String libelle, double prix) {
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		Pizza.compteur += 1;
		this.id = Pizza.compteur;
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
	public Pizza(int id, String code, String libelle, double prix) {
		this(code, libelle, prix);
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.code + "->" + this.libelle + " (" + this.prix + " €)";
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
		return (id + "," + code + "," + libelle + "," + prix);
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
	private void setId(int id) {
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
	private void setCode(String code) {
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
	private void setLibelle(String libelle) {
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
	private void setPrix(double prix) {
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

}
