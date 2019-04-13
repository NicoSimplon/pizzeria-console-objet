package fr.pizzeria.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
	
	public GestionFichier(String path) {
				
		try {
				
			this.fichier = new File(path);
				
			if (!fichier.exists()) {
				fichier.createNewFile();
			}
			
		} catch (IOException e) {
			
			throw new TechnicalException(e);
			
		}
	}
	
	public List<String> lecture() {
		
		try {
			
			String line = null;
			List <String> records = new ArrayList<>();
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
