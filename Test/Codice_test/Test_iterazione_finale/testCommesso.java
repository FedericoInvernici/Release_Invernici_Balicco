package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import record.Giochi;
import record.GiocoPrenotato;
import record.GiocoVenduto;
import record.Iscritto;
import commesso.CommessoModel;
import utility.GestoreJson;


class testCommesso {
	
	//Preparo l'ambiente per i test
	@BeforeClass public static void inizializza(){
		
		//Inserisco giochi nel database
		GestoreJson js = new GestoreJson();
		GestoreJson js1 = new GestoreJson();
		GestoreJson js2 = new GestoreJson();
		GestoreJson js3 = new GestoreJson();
		
		ArrayList<Giochi> giochiDaInserire = new ArrayList<>();
		for(int i=0;i<10;i++) {
			Giochi g = new Giochi("Prova"+i, 20.0+i , 10.0+i, i+5, i, i);
			giochiDaInserire.add(g);
		}
		js.inserisci("GIOCHI", giochiDaInserire);
		js.scritturaSuFile("FileGiochi.json");
		
		ArrayList<Iscritto> iscrittiDaInserire = new ArrayList<>();
		for(int i=0;i<10;i++) {
			Iscritto isc = new Iscritto("pippo"+i, "rossi"+i, "prova"+i+"@test.it");
			iscrittiDaInserire.add(isc);
		}
		js1.inserisci("ISCRITTI", iscrittiDaInserire);
		js1.scritturaSuFile("FileIscritti.json");
		
		ArrayList<GiocoPrenotato> giochiPreoDaInserire = new ArrayList<>();
		for(int i=0;i<10;i++) {
			GiocoPrenotato gp = new GiocoPrenotato("Prova"+i, 20.0+i , new Date(System.currentTimeMillis()), "prova"+i+"@test.it", "pippo1");
			giochiPreoDaInserire.add(gp);
		}
		js2.inserisci("GIOCHI PRENOTATI", giochiPreoDaInserire);
		js2.scritturaSuFile("FilePreordina.json");
		
		ArrayList<GiocoVenduto> giochiVendutiDaInserire = new ArrayList<>();
		for(int i=0;i<10;i++) {
			GiocoVenduto gv = new GiocoVenduto("prova"+i, 20.0+i, new Date(System.currentTimeMillis()), true);
			giochiVendutiDaInserire.add(gv);
		}
		js3.inserisci("GIOCHI VENDUTI", giochiVendutiDaInserire);
		js3.scritturaSuFile("FileVenduti.json");

		
		//Risultato dell'inizializzazione: riempire i 4 file con 10 istanze di dati, per verificare il funzionamento dei metodi di CommessoModel
	}
	

	@Test
	void testTrovaPrezzo() {
		inizializza();
		CommessoModel cm = new CommessoModel();
		assertEquals(20.0,cm.trovaprezzo("Prova0", true),"Il costo del gioco: Prova0 nuovo � di 20.0�");
		assertEquals(10.0,cm.trovaprezzo("Prova0", false),"Il costo del gioco: Prova0 usato � di 10.0�");
		assertEquals(25.0,cm.trovaprezzo("Prova5", true),"Il costo del gioco: Prova5 nuovo � di 25.0�");
		assertEquals(15.0,cm.trovaprezzo("Prova5", false),"Il costo del gioco: Prova5 usato � di 15.0�");
		assertNotEquals(100.0,cm.trovaprezzo("Prova7", true),"Il costo del gioco: Prova0 nuovo � di 27.0�");
	}
	@Test
	void testIscrivi() {
		inizializza();
		CommessoModel cm = new CommessoModel();
		cm.iscrivi("IscrittoProva", "ppp", "email@prova");
		ArrayList<Iscritto> isc = new ArrayList<>();
		GestoreJson js = new GestoreJson();
		isc=(ArrayList<Iscritto>) js.letturaDaFileJSON("FileIscritti.json");
		boolean trovato=false;
		for(int i=0;i<isc.size();i++) {
			if(isc.get(i).getNome().equals("IscrittoProva")) trovato=true;
		}	
		assertTrue(trovato,"Dopo avere letto il file degli iscritti dovrebbe trovarsi un iscritto con nome: IscrittoProva");
		
	}

	@Test
	void testAggiungiPreordine() {
		inizializza();
		CommessoModel cm = new CommessoModel();
		cm.preordina("Prova0", 20.0, new Date(System.currentTimeMillis()), "email@prova", "Pippo");
		cm.aggiungiPreordine("Prova1", "prova", "cliente");
		ArrayList<GiocoPrenotato> preo = new ArrayList<>();
		GestoreJson jspreo = new GestoreJson();
		preo=(ArrayList<GiocoPrenotato>) jspreo.letturaDaFileJSON("FilePreordina.json");
		boolean trovato=false;
		boolean trovato1=false;
		for(int i=0;i<preo.size();i++) {
			if(preo.get(i).getNome().equals("Prova0")) trovato=true;
			if(preo.get(i).getNome().equals("Prova1")) trovato1=true;
		}
			
		assertTrue(trovato,"Dopo avere letto il file dei giochi prenotati dovrebbe trovarsi un gioco con nome: Prova0");
		assertTrue(trovato1,"Dopo avere letto il file dei giochi prenotati dovrebbe trovarsi un gioco con nome: Prova1");
	}
	@Test
	void testRitiraUsato() {
		inizializza();
		CommessoModel cm= new CommessoModel();
		
		assertEquals(10.0,cm.ritirousato("Prova0"),"Il costo del gioco: Prova0 usato � di 10.0�");
		assertEquals(0.0,cm.ritirousato("Prova1000"),"Prova1000 non � non � nel database");
		
	}
	@Test
	void testVendita() {
		inizializza();
		CommessoModel cm = new CommessoModel();
		
		
		
		assertEquals(20.0,cm.vendita("Prova0",true),"Il costo del gioco: Prova0 nuovo � di 20.0�");
		assertEquals(0.0,cm.vendita("Prova0",false),"Prova0 non � disponibile usato");
		assertEquals(0.0,cm.vendita("Prova1000",true),"Non � presente nessun Prova1000 nel database");
	}
	@Test
	void testVerificaDisponibilit�() {
		inizializza();
		CommessoModel cm = new CommessoModel();


		assertTrue(cm.verificaDisponibilita("Prova0", true),"Il gioco Prova0 nuovo � disponibile in almeno una copia");
		assertFalse(cm.verificaDisponibilita("Prova0", false),"Il gioco Prova0 usato non � disponibile");
		assertFalse(cm.verificaDisponibilita("Prova400", true),"Il gioco Prova400 non � disponibile in quanto non � nel database");
	}
	@Test
	void testVerificaDisponibilit�Preo() {
		inizializza();
		CommessoModel cm = new CommessoModel();
		
		assertFalse(cm.verificaDisponibilitaPreo("Prova0"),"Il gioco Prova0 non ha nessuna disponibilit� per la prenotazione");
		assertTrue(cm.verificaDisponibilitaPreo("Prova7"),"Il gioco Prova7 � disponibile per essere prenotato");
		assertFalse(cm.verificaDisponibilitaPreo("Prova400"),"Il gioco Prova400 non � disponibile in quanto non � nel database");
	}

}
