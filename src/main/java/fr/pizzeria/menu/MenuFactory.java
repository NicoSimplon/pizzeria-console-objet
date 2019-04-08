package fr.pizzeria.menu;

/**
 * Crée un objet service à la demande
 * 
 * @author Nicolas
 *
 */
public class MenuFactory {

	public MenuService create(String typeservice) {
		
		if (typeservice.equals("lister")) {
			
			return new ListerPizzasService();
			
		} else if (typeservice.equals("ajouter")) {
			
			return new AjouterPizzaService();
			
		} else if (typeservice.equals("modifier")) {
			
			return new ModifierPizzaService();
			
		} else if (typeservice.equals("supprimer")) {
			
			return new SupprimerPizzaService();
			
		} else if (typeservice.equals("initialiser")) {
			
			return new InitialiserPizzaService();
			
		} else if (typeservice.equals("fermeture")) {
			
			return new FermetureService();
		
		}
		
		return null;
	}
}
