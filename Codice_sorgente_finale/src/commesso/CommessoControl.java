package commesso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import commesso_dialog.DialogIscrivi;
import commesso_dialog.DialogPreordina1;
import commesso_dialog.DialogPreordina2;
import commesso_dialog.DialogVendita;


public class CommessoControl {

	private CommessoView cview;
	private CommessoModel cmodel;
	
	public CommessoControl(CommessoView cv, CommessoModel cm) {
		cview=cv;
		cmodel=cm;
		cview.addAcqustanuovoListener(new Acquista());
		cview.addPreordinaListener(new preordina());
		cview.addUsatoListener(new usato());
		cview.addPrezzoListener(new prezzo());
		cview.addIscriviListener(new iscrivi());
	}
	
	//metodi dei button
	
	//Gestione pressione tasto "Acquista Gioco"
	class Acquista implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	new DialogVendita(cmodel); // richiamo il dialog per la vendita di giochi nuovi e usati
        }
	}
	
		
	//crea la query per la prenotazione dei giochi
	class preordina implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	new DialogPreordina1(cmodel);	
        }
	}
	
	//cerca il gioco scritto nel campo text, ne restituisce il 
	//prezzo e aumenta la quantità di usato di quel gioco 
	class usato implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String s, dialog;
        	double prezzo;
        	s = cview.getNomegiocoText();
        	prezzo= 0.7*cmodel.trovaprezzo(s, false); //cerco se il gioco è nel database e quindi può essere accettato
        	if(prezzo==0){ //gioco non presente nel database, messaggio di errore
        		dialog= "Gioco inesistente nel database, non può essere accettato";
        		JOptionPane.showMessageDialog(new JFrame(),dialog,"Errore",JOptionPane.ERROR_MESSAGE);
        	}else{ 
        		//Il gioco è presente e si chiede al cliente se l'importo offerto dal negozio è accettabile
        		dialog="Gioco presente nel database. \n Il valore del gioco è: " + prezzo + " €\n Il cliente accetta l'importo?";
        		int a = JOptionPane.showConfirmDialog(new JFrame(),dialog,"Conferma", JOptionPane.YES_NO_OPTION); //Se viene schiacciato il tasto Sì, si ritorna il valore 0
            	if (a==0) {
            		cmodel.ritirousato(s);
            	}
        	}
        	cview.setNomegiocoText("");
        }
	}
	
	//restituisce il prezzo del gioco col nome nel campo text
	class prezzo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String s;
        	double dn,du;
        	s = cview.getNomegiocoText();
        	dn=cmodel.trovaprezzo(s, true);
        	du=cmodel.trovaprezzo(s, false);
        	s=s+"p_nuovo: " + dn + " p_usato: " + du;
        	cview.setNomegiocoText(s);;
        }
	}
	
	//crea i campi necessari per l'iscrizione di nuovi clienti
	class iscrivi implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	new DialogIscrivi(cmodel);
        }
	}
	
}
