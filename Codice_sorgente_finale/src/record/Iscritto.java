package record;

import java.util.ArrayList;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Iscritto  implements JSONAware{

	String nome,cognome,email; 
	
	public Iscritto(String nome, String cognome, String email) {
		this.nome=nome;
		this.cognome=cognome;
		this.email=email; 
	}
	// Costruttore a partire da un JSONObject (letto da file)
	public Iscritto(JSONObject jso) {
		this.nome= (String) jso.get("Nome");
		this.cognome=(String) jso.get("Cognome");
		this.email=(String) jso.get("Email"); 
	}

	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public String toJSONString() {
		String s;
		s= "{\"Nome\":\"" + nome+"\", \"Cognome\":\"" + cognome + "\", \"Email\":\"" + email + "\"}";
		return s;
	}
}
