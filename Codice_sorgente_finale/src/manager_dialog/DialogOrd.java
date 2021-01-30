package manager_dialog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;

import manager_filiale.ManagerModel;

public class DialogOrd {

	private JFrame frmOrdinaUnGioco;
	private JTextField campoNomeGioc;
	JLabel etichettaNomeGioco;
	JLabel etichettaQuantita;
	JSpinner campoQuantita;
	JButton bottoneConferma;
	JLabel etichettaEsito;
	JButton bottoneAnnulla;
	String nomeGioco;
	int quantita;
	JTextPane pannelloEsito;
	
	ManagerModel mm = new ManagerModel();

	public DialogOrd(ManagerModel mm) {
		this.mm=mm;
		initialize();
	}

	private void initialize() {
		frmOrdinaUnGioco = new JFrame();
		frmOrdinaUnGioco.setTitle("Ordina un gioco al magazzino");
		frmOrdinaUnGioco.setBounds(100, 100, 515, 347);
		frmOrdinaUnGioco.getContentPane().setLayout(null);
		frmOrdinaUnGioco.setVisible(true);
		
		etichettaNomeGioco = new JLabel("Nome gioco da ordinare:");
		etichettaNomeGioco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		etichettaNomeGioco.setBounds(33, 13, 193, 16);
		frmOrdinaUnGioco.getContentPane().add(etichettaNomeGioco);
		
		campoNomeGioc = new JTextField();
		campoNomeGioc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		campoNomeGioc.setBounds(257, 10, 193, 22);
		frmOrdinaUnGioco.getContentPane().add(campoNomeGioc);
		campoNomeGioc.setColumns(10);
		
		etichettaQuantita = new JLabel("Quantit\u00E0:");
		etichettaQuantita.setFont(new Font("Tahoma", Font.PLAIN, 16));
		etichettaQuantita.setBounds(33, 71, 123, 16);
		frmOrdinaUnGioco.getContentPane().add(etichettaQuantita);
		
		SpinnerNumberModel model1 = new SpinnerNumberModel(0.0, 0.0, 10000.0 , 1.0);
		campoQuantita = new JSpinner(model1);
		campoQuantita.setFont(new Font("Tahoma", Font.PLAIN, 16));
		campoQuantita.setBounds(168, 68, 91, 22);
		frmOrdinaUnGioco.getContentPane().add(campoQuantita);


		
		bottoneConferma = new JButton("Conferma ordine");
		bottoneConferma.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bottoneConferma.setBounds(257, 126, 193, 40);
		frmOrdinaUnGioco.getContentPane().add(bottoneConferma);
		
		
		etichettaEsito = new JLabel("Esito ordine:");
		etichettaEsito.setFont(new Font("Tahoma", Font.PLAIN, 16));
		etichettaEsito.setBounds(33, 215, 123, 16);
		frmOrdinaUnGioco.getContentPane().add(etichettaEsito);
		etichettaEsito.setVisible(false);
		
		bottoneAnnulla = new JButton("Annulla");
		bottoneAnnulla.setBounds(353, 262, 97, 25);
		frmOrdinaUnGioco.getContentPane().add(bottoneAnnulla);
		
		pannelloEsito = new JTextPane();
		pannelloEsito.setBounds(196, 203, 199, 46);
		frmOrdinaUnGioco.getContentPane().add(pannelloEsito);
		pannelloEsito.setVisible(false);
		pannelloEsito.setEditable(false);
		
		// Gestione bottone annulla
		bottoneAnnulla.addActionListener(new ActionListener()  
		   {  
		       public void actionPerformed( ActionEvent e )  
		       {  

		           frmOrdinaUnGioco.setVisible(false);  
		       }  
		  }); 
		// Gestione del bottone Conferma
		bottoneConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	   nomeGioco=campoNomeGioc.getText(); // lettura del campo nome Gioco
		    	   quantita=(int)(double)campoQuantita.getValue(); // lettura della quantità
		    	   if(mm.cercaGioco(nomeGioco)&&quantita>0) ricercaPositiva(); // verifico che il gioco sia nel database e che la quantita sia maggiore di 0
		    	   else ricercaNegativa(); 
				
			}


		});
	}
	//Metodo chiamato in cui l'ordine è confermato
	private void ricercaPositiva() {
		mm.ordinaprodotto(nomeGioco, quantita); //richiamo il metodo che gestisce la richiesta e la inoltra al magazziono
		etichettaEsito.setVisible(true); //rendo visibile i campi per comunicare l'esito della richiesta
		pannelloEsito.setVisible(true);
		pannelloEsito.setText("Ordine effettuato con successo");
		
		
	}
	//Metodo chiamato in cui l'ordine è rifiutato (in caso il gioco non sia nel database o nel caso non sia stata indicata la quantità
	private void ricercaNegativa() {
		etichettaEsito.setVisible(true);
		pannelloEsito.setText("Impossibile effettuare ordine, controlla i dati e ritenta");
		pannelloEsito.setVisible(true);
	}
}
