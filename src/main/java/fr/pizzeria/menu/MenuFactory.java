package fr.pizzeria.menu;

/**
 * Crée un objet service à la demande
 * 
 * @author Nicolas
 *
 */
public class MenuFactory {

	private MenuService ms;
	
	public MenuService create(String typeservice) {
		
		if (typeservice.equals("lister")) {
			
			ms = new ListerPizzasService();
			
		} else if (typeservice.equals("ajouter")) {
			
			ms = new AjouterPizzaService();
			
		} else if (typeservice.equals("modifier")) {
			
			ms = new ModifierPizzaService();
			
		} else if (typeservice.equals("supprimer")) {
			
			ms = new SupprimerPizzaService();
			
		} else if (typeservice.equals("initialiser")) {
			
			ms = new InitialiserPizzaService();
			
		} else if (typeservice.equals("fermeture")) {
			
			ms = new FermetureService();
		
		}
		
		return ms;
	}
}
