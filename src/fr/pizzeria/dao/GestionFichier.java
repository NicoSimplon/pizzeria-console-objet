package fr.pizzeria.dao;

import java.io.*;
import java.util.*;

import fr.pizzeria.exception.TechnicalException;

/**
 * Gère la création, l'écriture et la lecture d'un fichier .txt
 * 
 * @author Nicolas
 *
 */
public class GestionFichier {

	public File fichier;
	public PrintWriter ecritureFichier;
	public BufferedReader lectureFichier;
	
	public GestionFichier() {
				
		try {
				
			this.fichier = new File("C:\\TEMP\\workspace\\pizzeria-console-objet\\save.txt");
				
			if (fichier.exists () == false) {
				fichier.createNewFile ();					
			}
			
		} catch (IOException e) {
			
			throw new TechnicalException(e);
			
		}
	}
	
	public List<String> lecture() {
		
		try {
			
			String line = null;
			List <String> records = new ArrayList <String> ();
			this.lectureFichier = new BufferedReader (new FileReader (this.fichier));	
			
			while ((line = this.lectureFichier.readLine ()) != null) {
				records.add(line);
			}
			
			this.lectureFichier.close ();
			return records;
			
		} catch (IOException e) {
			
			throw new TechnicalException(e);
		
		}
		
	}
	
	public void ecriture(List <String> listString) {
		
		try {
			
			this.ecritureFichier = new PrintWriter (new FileWriter (fichier));
			
			for (String element : listString)
				this.ecritureFichier.println (element);
			
			this.ecritureFichier.flush ();
			this.ecritureFichier.close ();
		
		} catch (IOException e) {
			
			throw new TechnicalException(e);
		
		}
		
	}
	
}
