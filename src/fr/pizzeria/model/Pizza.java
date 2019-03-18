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
		this(libelle, libelle, prix);
		this.id = id;
	}
	
	
	
	
}
