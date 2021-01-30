package utility;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import record.Giochi;
import record.GiocoPrenotato;
import record.GiocoVenduto;
import record.Iscritto;

public class ConvertitoreJSONObj {
	/*
	 * Metodo pubblico per la corretta conversione dei dati dal File JSON a ArrayList di oggetti.
	 * Nel caso il file non sia tra quelli gestiti viene restituito un ArrayList vuoto.
	 */
	static public ArrayList<?> converti(JSONObject vett, String file){
		ArrayList elenco = new ArrayList<>();
		switch(file) {
		case "FileGiochi.json": elenco = convertiGiochi(vett);
			break;
		case "FileIscritti.json": elenco = convertiIscritti(vett);
			break;
		case "FileVenduti.json": elenco = convertiVenduti(vett);
			break;
		case "FilePreordina.json": elenco = convertiPreordinati(vett);
			break;
		}
		return elenco; 
	}
	
	/*
	 * Metodi per la conversione dei dati in formato JSON su file in ArrayList di oggetti relativi al tipo di dato.
	 * Da notare che il parametro per la ricerca deve essere uguale alla chiave nel file JSON che corrisponde al vettore contenente
	 * le informazioni a ciascun record dei dati.
	 */
	private static ArrayList<?> convertiGiochi(JSONObject vett){
		JSONArray vetDiDati = (JSONArray) vett.get("GIOCHI"); 
		ArrayList<Giochi> listaGiochi = new ArrayList<Giochi>();
		if (vetDiDati==null) return listaGiochi;
		for(Object o:vetDiDati) {
			listaGiochi.add(new Giochi((JSONObject)o));
		}
		return listaGiochi;
		
	}
	private static ArrayList<?> convertiIscritti(JSONObject vett){
		JSONArray vetDiDati = (JSONArray) vett.get("ISCRITTI"); 
		ArrayList<Iscritto> listaIscritti = new ArrayList<Iscritto>();
		for(Object o:vetDiDati) {
			listaIscritti.add(new Iscritto((JSONObject)o));
		}
		return listaIscritti;
	}
	private static ArrayList<?> convertiVenduti(JSONObject vett){
		JSONArray vetDiDati = (JSONArray) vett.get("GIOCHI VENDUTI"); 
		ArrayList<GiocoVenduto> listaGiochiV = new ArrayList<GiocoVenduto>();
		for(Object o:vetDiDati) {
			listaGiochiV.add(new GiocoVenduto((JSONObject)o));
		}
		return listaGiochiV;
	}
	private static ArrayList<?> convertiPreordinati(JSONObject vett){
		JSONArray vetDiDati = (JSONArray) vett.get("GIOCHI PRENOTATI"); 
		ArrayList<GiocoPrenotato> listaGiochiP = new ArrayList<GiocoPrenotato>();
		for(Object o:vetDiDati) {
			listaGiochiP.add(new GiocoPrenotato((JSONObject)o));
		}
		return listaGiochiP;
	}
	

}
