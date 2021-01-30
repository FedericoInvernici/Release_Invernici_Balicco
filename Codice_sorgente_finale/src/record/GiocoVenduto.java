package record;

import java.util.Date;
import java.util.spi.TimeZoneNameProvider;

import javax.xml.crypto.Data;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class GiocoVenduto extends Gioco implements JSONAware{

	double prezzo;
	Date data_vendita;
	boolean nuovo;
	
	public GiocoVenduto(String nome, Double prezzo, Date d, boolean nuovo) {
		super(nome);
		this.prezzo=prezzo;
		this.data_vendita=d;
		this.nuovo=nuovo;
	}
	// Costruttore a partire da un JSONObject (letto da file)
	public GiocoVenduto(JSONObject jso) {
		nome=(String) jso.get("Nome");
		this.prezzo=(double) jso.get("prezzo");
		this.data_vendita= new Date((int)(long)jso.get("anno"), (int)(long)jso.get("mese"), (int)(long)jso.get("giorno"));
		this.nuovo=(boolean) jso.get("Nuovo");
	}

	public Date getData_vendita() {
		return data_vendita;
	}
	
	public void setData_vendita(Date data_vendita) {
		this.data_vendita = data_vendita;
	}
	
	public double getPrezzo() {
		return prezzo;
	}

	@Override
	public String toJSONString() {
		String s;
		s= "{\"Nome\":\"" + nome+"\", \"prezzo\":" + prezzo + ", \"anno\":" + data_vendita.getYear() +
				", \"mese\":" + data_vendita.getMonth() + ", \"giorno\":" + data_vendita.getDate() +
				", \"Nuovo\":" + nuovo + "}";
		return s;
	}
	
}
