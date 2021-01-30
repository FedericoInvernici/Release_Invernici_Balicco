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
	
	private static void riempiArray(int[] vett) {
		for( int i=0; i<vett.length;i++) {
			vett[i]=i;
		}
	}
	
	private static double calcolaTempoBin(int[] vett, int iterazioni, int valore) {
		double inizioBinary = System.currentTimeMillis();
		for(int i=0;i<iterazioni;i++)
			TrovaElementoBinInt(vett, valore);
		double fineBinary = System.currentTimeMillis();
		double tempoTotaleBinary = (fineBinary-inizioBinary)*0.001;
		return tempoTotaleBinary;
	}
	private static double calcolaTempoInterp(int[] vett, int iterazioni, int valore) {
		double inizioInterp = System.currentTimeMillis();
		for(int i=0;i<iterazioni;i++)
			TrovaElementoInterp(vett, valore);
		double fineInterp = System.currentTimeMillis();
		double tempoTotaleInterp = (fineInterp-inizioInterp)*0.001;
		return tempoTotaleInterp;
	}
	
	/*
	//Metodo che calcola il tempo di esecuzioni della ricerca binaria ripetuta un numero "iterazioni" di volte
	private  static <T extends Comparable> double  calcolaTempoBin(ArrayList<T> vettore, int iterazioni, T valore) {
		double inizioBinary = System.currentTimeMillis();
		for(int i=0;i<iterazioni;i++)
			TrovaElementoBin(vettore, valore);
		double fineBinary = System.currentTimeMillis();
		double tempoTotaleBinary = (fineBinary-inizioBinary)*0.001;
		return tempoTotaleBinary;
	}
	
	//Metodo che calcola il tempo di esecuzioni della ricerca interpolata ripetuta un numero "iterazioni" di volte
	private static <T extends Comparable> double calcolaTempoInt(ArrayList<T> vettore, int iterazioni, T valore) {
		double inizioInterpolata = System.currentTimeMillis();
		for(int i=0;i<iterazioni;i++)
			TrovaElementoComp(vettore, valore);
		double fineInterpolata = System.currentTimeMillis();
		
		double tempoTotaleInterpolata = (fineInterpolata-inizioInterpolata)*0.001;
		return tempoTotaleInterpolata;
	}
	
	
	
	
	
	// Metodi di ricerca
	
	//Ricerca interpolata
	public static <T extends Comparable> int TrovaElementoComp(ArrayList<T> vett, T elem) {
		return TrovaElementoCompInt(vett, elem, 0, vett.size()-1);
	}


	private static <T extends Comparable> int TrovaElementoCompInt(ArrayList<T> vett, T elem, int iniz, int fine) {
		if(iniz>=fine) return -1; //previene la divisione per zero nel calcolo dell'indice
		int indice= iniz + Math.round((fine-iniz)*((float)elem.compareTo(vett.get(iniz))/
				(float)vett.get(fine).compareTo(vett.get(iniz))));
		if(indice<iniz||indice>fine) return -1;
		else if(vett.get(indice).compareTo(elem)==0) return indice;
		else if(vett.get(indice).compareTo(elem)<0) return TrovaElementoCompInt(vett, elem, indice+1, fine);
		else return TrovaElementoCompInt(vett, elem, iniz, indice-1);	
	}
	
	
	
	//Ricerca binaria
	public static <T extends Comparable> int TrovaElementoBin(ArrayList<T> vett, T elem) {
		return TrovaElementoBinInt(vett, elem, 0, vett.size()-1);
	}
	private static <T extends Comparable> int TrovaElementoBinInt(ArrayList<T> vett, T elem, int iniz, int fine) {
		if(iniz>=fine) return -1; //previene la divisione per zero nel calcolo dell'indice
		int indice= (fine+iniz)/2;
		if(indice<iniz||indice>fine) return -1;
		else if(vett.get(indice).compareTo(elem)==0) return indice;
		else if(vett.get(indice).compareTo(elem)<0) return TrovaElementoCompInt(vett, elem, indice+1, fine);
		else return TrovaElementoCompInt(vett, elem, iniz, indice-1);	
	}*/
	
	public static int TrovaElementoInterp(int[] vett, int elem) {
		return TrovaElementoCompInterp(vett, elem, 0, vett.length-1);
	}


	private static int TrovaElementoCompInterp(int[] vett, int elem, int iniz, int fine) {
		if(iniz>=fine) return -1; //previene la divisione per zero nel calcolo dell'indice
		//int indice= iniz + Math.round((fine-iniz)*((float)elem.compareTo(vett.get(iniz))/
		//		(float)vett.get(fine).compareTo(vett.get(iniz))));
		int indice= iniz + Math.round((fine-iniz)*(float)(elem-vett[iniz])/(float)(vett[fine]-vett[iniz]));

		if(indice<iniz||indice>fine) return -1;
		else if(vett[indice]==elem) return indice;
		else if(vett[indice]<elem) return TrovaElementoCompInterp(vett, elem, indice+1, fine);
		else return TrovaElementoCompInterp(vett, elem, iniz, indice-1);	
	}
	
	public static int TrovaElementoBinInt(int[] vett, int elem) {
		return TrovaElementoBinIntInterno(vett, elem, 0, vett.length-1);
	}


	private static int TrovaElementoBinIntInterno(int[] vett, int elem, int iniz, int fine) {
		if(iniz>=fine) return -1; //previene la divisione per zero nel calcolo dell'indice
		//int indice= iniz + Math.round((fine-iniz)*((float)elem.compareTo(vett.get(iniz))/
		//		(float)vett.get(fine).compareTo(vett.get(iniz))));
		int indice= (iniz + fine)/2;
		if(indice<iniz||indice>fine) return -1;
		else if(vett[indice]==elem) return indice;
		else if(vett[indice]<elem) return TrovaElementoCompInterp(vett, elem, indice+1, fine);
		else return TrovaElementoCompInterp(vett, elem, iniz, indice-1);	
	}
}
