package news_letter;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import finte_applicazioni_esterne.Finto_gestore_mail;
import record.Iscritto;
import utility.GestoreJson;

public class NewsModel {

	ArrayList<Iscritto> iscr = new ArrayList<>();	//utilizzo arraylist per testare
	Finto_gestore_mail gm = new Finto_gestore_mail();
	
	public NewsModel() { //costruttore legge i file
		letturaDaFileIscritti();
	}
	
	//invia la mail testo a ogni iscritto alla newsletter
	public void spedisci(String testo) { 
		for (int i = 0; i < iscr.size(); i++) {  
			gm.InviaMailPerFinta(iscr.get(i).getEmail(), testo);
		}
	}
	
	
	//METODI SUI FILE
		
	public void letturaDaFileIscritti() {
		GestoreJson js = new GestoreJson();
		js.letturaDaFileJSON("FileIscritti.json");
	}
	
}
