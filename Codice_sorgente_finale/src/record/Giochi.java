package record;

import java.lang.reflect.Constructor;
import java.util.Date;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;



public class Giochi  implements JSONAware,Comparable{

	String nome;
	double prezzo_nuovo;
	double prezzo_usato;
	int quantita_nuovo;
	int quantita_usato;
	int quantita_preordinabile;
	Date dataUscita; //diverso da 0 solo se il gioco non è ancora uscito
	
	public Giochi(String nome, Double prezzo_nuovo, Double prezzo_usato, int qnuovo, int qusato, int qprenotable) {
		this.nome=nome;
		this.prezzo_nuovo=prezzo_nuovo;
		this.prezzo_usato=prezzo_usato;
		this.quantita_nuovo=qnuovo;
		this.quantita_usato=qusato;
		this.quantita_preordinabile=qprenotable;
		dataUscita=new Date();
	}
	

	public Giochi(String string, int i, int j, int k) {
		this.nome=string;
		this.quantita_nuovo=i;
		this.quantita_usato=j;
		this.quantita_preordinabile=k;
	}
	
	// Costruttore a partire da un JSONObject (letto da file)
	public Giochi(JSONObject jso) {
		this.nome= (String) jso.get("nome");
		this.prezzo_nuovo=(double) jso.get("p_nuovi");
		this.prezzo_usato=(double) jso.get("p_usati");
		this.quantita_nuovo= (int)(long)jso.get("q_nuovi");
		this.quantita_usato=(int)(long) jso.get("q_usati");
		this.quantita_preordinabile=(int)(long) jso.get("q_preordinabili");
		this.dataUscita= new Date((int)(long) jso.get("anno"),(int)(long) jso.get("mese"),(int)(long) jso.get("giorno"));
	}
	
	public void setDataUscita(String giorno, String mese, String anno) {
		this.dataUscita=new Date(Integer.parseInt(anno),Integer.parseInt(mese),Integer.parseInt(giorno));
	}

	public String getNome() {
		return nome;
	}
	
	public void setPrezzo_nuovo(float prezzo_nuovo) {
		this.prezzo_nuovo = prezzo_nuovo;
	}
	
	public void setPrezzo_usato(float prezzo_usato) {
		this.prezzo_usato = prezzo_usato;
	}

	public double getPrezzo_nuovo() {
		return prezzo_nuovo;
	}
	
	public double getPrezzo_usato() {
		return prezzo_usato;
	}
	
	public int getQuantita_nuovo() {
		return quantita_nuovo;
	}
	public int getQuantita_usato() {
		return quantita_usato;
	}
	
	public int getQuantita_preo() {
		return quantita_preordinabile;
	}
	public Date getDataUscita() {
		return dataUscita;
	}
	public void setQuantitaDisponibilePreoMenoMeno() {
		this.quantita_preordinabile--;
	}
	
	public void aggiungiQnuovo(int q) {
		this.quantita_nuovo=this.quantita_nuovo+q;
	}
	
	public void aggiungiQusato(int q) {
		this.quantita_usato=this.quantita_usato+q;
	}
	
	public void quantitaUsatopiupiu() {
		this.quantita_usato++;
	}
	
	public void quantitaUsatomenomeno() {
		this.quantita_usato--;
	}
	
	public void quantitaNuovomenomeno() {
		this.quantita_nuovo--;
	}
	/*
	 * Metodo dell'interfaccia JSONAware, che richiede di restituire i dati della classe in un formato compatibile al JSON.
	 * La stringa deve essere perciò conforme agli standard per essere parsata correttamente.
	 */
	public String toJSONString() {
		String s;
		s= "{\"nome\":\"" + nome+"\", \"p_nuovi\":" + prezzo_nuovo + ", \"p_usati\":" + prezzo_usato + ", \"q_nuovi\":" + quantita_nuovo +
				", \"q_usati\":" + quantita_usato + ", \"q_preordinabili\":" + quantita_preordinabile+ ", \"anno\":" + dataUscita.getYear() +
				", \"mese\":" + dataUscita.getMonth() + ", \"giorno\":" + dataUscita.getDate()+"}";
		return s;
	}
	
	public int compareTo(Object o){
		return this.getNome().compareTo(((Giochi) o).getNome());
	}
	
	public boolean equals(Object o) {
		if (o instanceof Giochi&&this.getNome().equals(((Giochi)o).getNome())) return true;
		else return false;
	}

	
	
}
