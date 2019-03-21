package fr.pizzeria.model;

public enum CategoriePizza {

	VIANDE("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans Viande");

	private String type;

	private CategoriePizza(String type) {
		this.type = type;
	}

	public String toString() {
		return this.type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
