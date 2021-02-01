package test;

import java.util.ArrayList;
import java.util.Collections;

import record.Giochi;
import utility.RicercaInterpolata;

public class testVelocitaRicerca {
	
	public static void main(String[] args) {
		
		ArrayList<String> vettore = new ArrayList<>();
		int vett[] = new int[100000];
		riempiArray(vett);
		int iterazioni = 100000;
		int valoreRicerca = 49999;
		
		//double tempoTotaleBin = calcolaTempoBin(vett, iterazioni, valoreRicerca);
		double tempoTotaleInt = calcolaTempoInterp(vett, iterazioni, valoreRicerca);
		
		//System.out.println("Il tempo totale per la ricerca binaria è: " + tempoTotaleBin);
		System.out.println("Il tempo totale per la ricerca interpolata è: "+ tempoTotaleInt);
		

		//System.out.println(TrovaElementoBinInt(vett, valoreRicerca));
		System.out.println(TrovaElementoInterp(vett, valoreRicerca));
		System.out.println(vett.length);
		
	}
	
	//Metodo per il riempimento dell'array con numeri interi da 0 alla sua lunghezza - 1
	private static void riempiArray(int[] vett) {
		for( int i=0; i<vett.length;i++) {
			vett[i]=i;
		}
	}
	/* Metodo per il calcolo del tempo impiegato per un numero di iterazioni (passato come parametro)
	 * dell'algoritmo di ricerca binaria sul vettore passato come parametro ricercando l'intero contenuto in
	  "valore"
	*/
	private static double calcolaTempoBin(int[] vett, int iterazioni, int valore) {
		double inizioBinary = System.currentTimeMillis();
		for(int i=0;i<iterazioni;i++)
			TrovaElementoBinInt(vett, valore);
		double fineBinary = System.currentTimeMillis();
		double tempoTotaleBinary = (fineBinary-inizioBinary)*0.001;
		return tempoTotaleBinary;
	}
	/* Metodo per il calcolo del tempo impiegato per un numero di iterazioni (passato come parametro)
	 * dell'algoritmo di ricerca interpolata sul vettore passato come parametro ricercando l'intero contenuto in
	  "valore"
	*/
	private static double calcolaTempoInterp(int[] vett, int iterazioni, int valore) {
		double inizioInterp = System.currentTimeMillis();
		for(int i=0;i<iterazioni;i++)
			TrovaElementoInterp(vett, valore);
		double fineInterp = System.currentTimeMillis();
		double tempoTotaleInterp = (fineInterp-inizioInterp)*0.001;
		return tempoTotaleInterp;
	}
	
	
	/*
	 * Metodi per la ricerca interpolata in un vettore di interi di interi
	 */
	
	public static int TrovaElementoInterp(int[] vett, int elem) {
		return TrovaElementoCompInterp(vett, elem, 0, vett.length-1);
	}


	private static int TrovaElementoCompInterp(int[] vett, int elem, int iniz, int fine) {
		if(iniz>=fine) return -1; //previene la divisione per zero nel calcolo dell'indice
		int indice= iniz + Math.round((fine-iniz)*(float)(elem-vett[iniz])/(float)(vett[fine]-vett[iniz]));

		if(indice<iniz||indice>fine) return -1;
		else if(vett[indice]==elem) return indice;
		else if(vett[indice]<elem) return TrovaElementoCompInterp(vett, elem, indice+1, fine);
		else return TrovaElementoCompInterp(vett, elem, iniz, indice-1);	
	}
	
	
	
	/*
	 * Metodi per la ricerca binaria in un vettore di interi
	 */
	
	public static int TrovaElementoBinInt(int[] vett, int elem) {
		return TrovaElementoBinIntInterno(vett, elem, 0, vett.length-1);
	}


	private static int TrovaElementoBinIntInterno(int[] vett, int elem, int iniz, int fine) {
		if(iniz>=fine) return -1; 
		int indice= (iniz + fine)/2;
		if(indice<iniz||indice>fine) return -1;
		else if(vett[indice]==elem) return indice;
		else if(vett[indice]<elem) return TrovaElementoCompInterp(vett, elem, indice+1, fine);
		else return TrovaElementoCompInterp(vett, elem, iniz, indice-1);	
	}
}
