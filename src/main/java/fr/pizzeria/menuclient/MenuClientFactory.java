package fr.pizzeria.menuclient;

/**
 * Fournit les services du menu client à la demande de celui-ci
 * 
 * @author Nicolas
 *
 */
public class MenuClientFactory {
	
	private MenuClientService ms;

	public MenuClientService create(String typeservice) {

		if (typeservice.equals("inscription")) {

			ms = new InscriptionClientService();

		} else if (typeservice.equals("connexion")) {

			ms = new ConnexionClientService();

		} 
		return ms;
	}
}
