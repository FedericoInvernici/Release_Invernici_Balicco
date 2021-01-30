package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import Manager_filiare.ManagerModel;
import Record.Giochi;
import Record.GiocoVenduto;
import utility.GestoreJson;

class testManager {
	//Preparo l'ambiente per i test
		@BeforeClass public static void inizializza(){
			
			
			//Inserisco giochi nel database
			GestoreJson js = new GestoreJson();
			GestoreJson js1 = new GestoreJson();
			
			ArrayList<Giochi> giochiDaInserire = new ArrayList<>();
			for(int i=0;i<10;i++) {
				Giochi g = new Giochi("Prova"+i, 20.0+i , 10.0+i, i+5, i, i);
				giochiDaInserire.add(g);
			}
			js.inserisci("GIOCHI", giochiDaInserire);
			js.scritturaSuFile("FileGiochi.json");
		
			
			ArrayList<GiocoVenduto> giochiVendutiDaInserire = new ArrayList<>();
			for(int i=0;i<10;i++) {
				GiocoVenduto gv = new GiocoVenduto("prova"+i, 20.0+i, new Date(System.currentTimeMillis()), true);
				giochiVendutiDaInserire.add(gv);
			}
			js1.inserisci("GIOCHI VENDUTI", giochiVendutiDaInserire);
			js1.scritturaSuFile("FileVenduti.json");
			
			//Risultato dell'inizializzazione: riempire i 2 file di interesse per il manager con 10 istanze di dati, per verificare il funzionamento dei metodi di CommessoModel
		}
	@Test
	void testAggiungiGioco() {
		inizializza();
		ManagerModel mm = new ManagerModel();
		
		mm.aggiungigiochi("GiocoDiProva", 20, 10);
		
		ArrayList<Giochi> g = new ArrayList<>();
		GestoreJson js = new GestoreJson();
		g=(ArrayList<Giochi>) js.letturaDaFileJSON("FileGiochi.json");
		boolean trovato=false;
		for(int i=0;i<g.size();i++)
			if(g.get(i).getNome().equals("GiocoDiProva")) trovato=true;
			
		assertTrue(trovato,"Dopo avere letto il file dei gioco dovrebbe trovare il nuovo gioco aggiunto nominato: GiocoDiProva");
		
	}

	@Test
	void testCambiaPrezzo() {
		inizializza();
		ManagerModel mm = new ManagerModel();
		mm.cambiaprezzo("Prova4", true, (float) 60.8); //cambio il prezzo di uno dei gioco presenti a database
		
		ArrayList<Giochi> g = new ArrayList<>();
		GestoreJson js = new GestoreJson();
		g=(ArrayList<Giochi>) js.letturaDaFileJSON("FileGiochi.json");
		double prezzo=0.0;
		for(int i=0;i<g.size();i++)
			if(g.get(i).getNome().equals("Prova4")) prezzo=g.get(i).getPrezzo_nuovo();
			
		assertEquals((float)60.8,prezzo,"Il prezzo del gioco nuovo: Prova4, dovrebbe essere modificato da 24.0 a 60.8");
	}
	

	@Test
	void testReport() {
		inizializza();
		ManagerModel mm = new ManagerModel();
		//Dovrei ottenere tutti i 10 giochi nel file Giochi venduti, poichè tutti quanti sono stati registrati come venduti il giorno stesso
		//della compilazione del report e il report è degli ultimi 10 mesi.
		String report = mm.reportdata(10); 
		String reportIdeale = "";
		ArrayList<GiocoVenduto> gv = new ArrayList<>();
		GestoreJson js = new GestoreJson();
		gv=(ArrayList<GiocoVenduto>) js.letturaDaFileJSON("FileVenduti.json");
		// Costruisco come dovrebbe essere il report che dovrei ricevere
		for (int i = 0; i < gv.size(); i++) {
			reportIdeale = reportIdeale + " " + gv.get(i).getNome() + " " + gv.get(i).getPrezzo() + "\n";
		}
		
		assertEquals(reportIdeale, report, "Il report dovrebbe contenere tutti e dieci i giochi nel database dei venduti");
	}
	@Test
	void testAggiungiQuantitaGiochi() {
		fail("Not yet implemented");
	}
	@Test
	void testAggiungiGiocoPrenotabile() {
		fail("Not yet implemented");
	}

}
