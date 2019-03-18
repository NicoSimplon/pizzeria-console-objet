package fr.pizzeria.model;

public class Pizza {
	
	private int id;
	private String code;
	private String libelle;
	private double prix;
	private static int compteur;
	
	public Pizza (String code, String libelle, double prix) {
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		Pizza.compteur += 1;
		this.id = Pizza.compteur;
	}
	
	public Pizza (int id, String code, String libelle, double prix) {
		this(code, libelle, prix);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public void affichage() {
		System.out.println(this.code + "->" + this.libelle + " (" + this.prix + " €)");
	}
	
}
