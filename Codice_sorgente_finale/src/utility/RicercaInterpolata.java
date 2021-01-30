package utility;

import java.lang.Math;
import java.util.ArrayList;

public class RicercaInterpolata {
	
	/* Metodo richiamabile per la ricerca in un vettore di oggetti che implementano l'interfaccia "ValoreIntero".
	 * Parametri: vett[] = vettore dove effettuare la ricerca
	 			  elem = elemento da ricercare
	 */
	static public <T extends ValoreIntero> boolean TrovaElemento(T vett[], T elem) {
		return TrovaElementoInterp(vett, elem, 0, vett.length-1);
	}
	
	/* Metodo interno ricorsivo per la ricerca interpolata in vettori di oggetti con interfaccia "ValoreIntero"
	 * 
	 */
	public static <T extends ValoreIntero> boolean TrovaElementoInterp(T vett[], T elem, int iniz, int fine) {
		int indice= iniz + Math.round((fine-iniz)*((float)elem.interoPerRicerca()-(float)vett[iniz].interoPerRicerca())/
				((float)vett[fine].interoPerRicerca()-(float)vett[iniz].interoPerRicerca()));
		System.out.println("Inizio:"+iniz+"  Fine: "+ fine +" Indice: "+ indice);
		if(indice<iniz||indice>fine) return false;
		else if(vett[indice].interoPerRicerca()==elem.interoPerRicerca()) return true;
		else if(vett[indice].interoPerRicerca()<elem.interoPerRicerca()) return TrovaElementoInterp(vett, elem, indice, fine);
		else if(vett[indice].interoPerRicerca()>elem.interoPerRicerca()) return TrovaElementoInterp(vett, elem, iniz, indice);
		else return false;		
	}
	
	/* Metodo richiamabile per la ricerca in un vettore di oggetti che implementano l'interfaccia "Comparable".
	 * Pensato soprattutto per la ricerca con le Stringhe
	 * Parametri: vett = ArrayList dove effettuare la ricerca
	 			  elem = elemento da ricercare
	   Viene restituito l'indice dell'elemento se presente nell'ArrayList, altrimenti viene restituito -1		  
	 */
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
	
	public static <T extends Comparable> int TrovaElementoBin(ArrayList<T> vett, T elem) {
		return TrovaElementoBinInt(vett, elem, 0, vett.size()-1);
	}
	private static <T extends Comparable> int TrovaElementoBinInt(ArrayList<T> vett, T elem, int iniz, int fine) {
		if(iniz>=fine) return -1; //previene la divisione per zero nel calcolo dell'indice
		int indice= (fine-iniz)/2;
		if(indice<iniz||indice>fine) return -1;
		else if(vett.get(indice).compareTo(elem)==0) return indice;
		else if(vett.get(indice).compareTo(elem)<0) return TrovaElementoCompInt(vett, elem, indice+1, fine);
		else return TrovaElementoCompInt(vett, elem, iniz, indice-1);	
	}
	
}
