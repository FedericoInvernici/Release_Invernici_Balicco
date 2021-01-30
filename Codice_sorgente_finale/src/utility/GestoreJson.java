package utility;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.*; //Libreria per la gestione del formato JSON
import org.json.simple.parser.JSONParser; // classe per il parsing di un file JSON
import org.json.simple.parser.ParseException; //classe per la gestione di parsing

import record.Giochi;


/*classe di collegamento con la libreria org.json  */
public class GestoreJson {
	
	// Oggetto in cui inserire le coppie di chiave e valore in formato JSON
	JSONObject dati = new JSONObject();
	
	/*/metodo di inserimento nell'oggetto JSON di una coppia chiave (obbligatoriamente di tipo stringa)
	 * e un oggetto di qualsiasi tipo */
	public <T> void inserisci(String chiave, T valore) {
		dati.put(chiave, valore);
	}
	
	/*/metodo di inserimento nell'oggetto JSON di una coppia chiave (obbligatoriamente di tipo stringa)
	 * e un vettore di oggetti di qualsiasi tipo */
	public <T> void inserisci(String chiave, T[] vettoreDiValori) {
		dati.put(chiave, vettoreDiValori);
	}
	
	/* Metodo che si basa sul toJSONString 
	 * */
	public String toString() {
		return dati.toJSONString();
	}
	
	/*Metodo per la creazione di un nuovo file JSON nella cartella "data"
	 * come parametro riceve la stringa contenete il nome da dare al nuovo file 
	 */
	public void creaFileJson(String nome) {
		try {
			File nuovoFile = new File("data\\"+nome+".json");
		    if (nuovoFile.createNewFile()) {
		    	System.out.println("Nuovo file creato correttamente: " + nuovoFile.getName());
		    } else {
		    	System.out.println("Il file esiste già");
		    }
		} catch (IOException e) {
			System.out.println("Problema nella generazione del file");
		    e.printStackTrace();
		}		
	}
	
	/*Metodo per la scrittura su file già esistente, di cui bisogna fornire il nome (compreso di estensione) nel parametro
	 *"nomeFile". Viene scritto il contenuto del campo JSONObject "dati" nel file indicato.
	 */
	public void scritturaSuFile(String nomeFile) {
		try {
			FileWriter fileInput = new FileWriter("data\\"+nomeFile); //se metto come secondo argomento "true", la stringa viene messa in coda
			//fileInput.write(testo);
			dati.writeJSONString(fileInput);
			fileInput.close();
		} catch(IOException e){
			System.out.println("Errore nell'accesso/scrittura al file");
		} 
	}

	
	/*
	 * Riceve come argomento il nome del file (compreso di estensione) da cui ricavare i dati in formato JSON.
	 * Tramite il parsing restituisce l'ArrayList di oggetti corrispondente in base a quale dei quattro file viene letto 
	 * (in caso di aggiunta di ulteriori file JSON occorrerebbero delle modifiche minori).
	 * ES: se viene chiamato il letturaDaFileJSON("FileGiochi") viene restituito un ArrayList<Giochi>.
	 */
	public ArrayList<?> letturaDaFileJSON(String nomeFile) {
		JSONParser parser = new JSONParser(); //oggetto per il parsing da file
		
		Object ogg = null;
		JSONObject datiDaFile = null;
		ArrayList<?> recordDiDati = new ArrayList<>();
		try {
			FileReader fileOutput = new FileReader("data\\"+nomeFile);
			ogg = parser.parse(fileOutput);
			fileOutput.close();
		}catch(IOException e){
			System.out.println("Errore nell'accesso al file");
		} catch (ParseException e) {
			System.out.println("Errore nel parsing");
		} 
		datiDaFile = (JSONObject) ogg;
		recordDiDati = ConvertitoreJSONObj.converti(datiDaFile, nomeFile);
		return recordDiDati;
	}

	

}

