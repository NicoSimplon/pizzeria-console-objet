package fr.pizzeria.model;

public enum CategoriePizza {

	VIANDE("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans_Viande"), DEFAULT("Default");

	private String type;

	private CategoriePizza(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}

	public String getType() {
		return type;
	}

	void setType(String type) {
		this.type = type;
	}

}
