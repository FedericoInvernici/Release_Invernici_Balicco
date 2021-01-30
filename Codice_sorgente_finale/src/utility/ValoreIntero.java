package utility;

/* L'interfaccia serve a indicare un campo di tipo intero della classe che la implementa in modo tale
 * da fare la ricerca interpolata sulla base di quel campo.
 */
public interface ValoreIntero {

	/* L'unico metodo dell'interfaccia restituisce il valore del campo (un int), che verrà chiamato nell'esecuzione
	 * del metodo TrovaElemento della classe RicercaInterpolata
	 * 
	 */
	public int interoPerRicerca();

}
