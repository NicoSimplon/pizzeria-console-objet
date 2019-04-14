package fr.pizzeria.model;

public enum Statut {

	FINIE("Finie"), EN_ATTENTE("En_Attente");
	
	private String statutPizza;
	
	private Statut(String statutPizza) {
		this.setStatutPizza(statutPizza);
	}

	/**
	 * @return the statutPizza
	 */
	public String getStatutPizza() {
		return statutPizza;
	}

	/**
	 * @param statutPizza the statutPizza to set
	 */
	private void setStatutPizza(String statutPizza) {
		this.statutPizza = statutPizza;
	}
	
}
