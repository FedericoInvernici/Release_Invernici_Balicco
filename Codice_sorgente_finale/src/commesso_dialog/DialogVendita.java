package commesso_dialog;

import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import commesso.CommessoModel;
import finte_applicazioni_esterne.Finto_pagamento_carta;

public class DialogVendita {

	private JFrame frame;
	private JTextField campoPrezzo;
	private JTextField campoNomeGioco;
	JLabel EtichettaNomeGioco;
	JRadioButton BottoneGiocoNuovo;
	JRadioButton BottoneGiocoUsato;
	ButtonGroup bg;
	JButton BottoneGioco;
	JLabel etichettaPrezzo;
	JComboBox boxMetodoPagamento;
	JButton pagamento;
	JLabel etichettaPagamento;
	JButton bottoneAnnulla;
	CommessoModel cm;
	boolean nuovo;
	String nomeGioco;


	public DialogVendita(CommessoModel cm) {
		this.cm=cm;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 614, 402);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		EtichettaNomeGioco = new JLabel("Indicare il nome del gioco:");
		EtichettaNomeGioco.setBounds(38, 40, 184, 32);
		frame.getContentPane().add(EtichettaNomeGioco);
		
		BottoneGiocoNuovo = new JRadioButton("Videogioco Nuovo");
		BottoneGiocoNuovo.setBounds(99, 81, 172, 25);
		

		BottoneGiocoUsato = new JRadioButton("Videogioco Usato");
		BottoneGiocoUsato.setBounds(275, 81, 127, 25);
		
		bg = new ButtonGroup();
		bg.add(BottoneGiocoUsato);
		bg.add(BottoneGiocoNuovo);
		BottoneGiocoNuovo.setSelected(true);

		frame.getContentPane().add(BottoneGiocoUsato);
		frame.getContentPane().add(BottoneGiocoNuovo);
		
		campoNomeGioco = new JTextField();
		campoNomeGioco.setBounds(228, 40, 236, 32);
		frame.getContentPane().add(campoNomeGioco);
		campoNomeGioco.setColumns(10);
		
		BottoneGioco = new JButton("Verifica disponibilità");
		BottoneGioco.setBounds(366, 115, 172, 32);
		frame.getContentPane().add(BottoneGioco);
		
		campoPrezzo = new JTextField();
		campoPrezzo.setBounds(191, 187, 155, 32);
		frame.getContentPane().add(campoPrezzo);
		campoPrezzo.setColumns(10);
		campoPrezzo.setEditable(false);
		campoPrezzo.setVisible(false);
		
		etichettaPrezzo = new JLabel("Risultato ricerca:");
		etichettaPrezzo.setBounds(38, 195, 143, 16);
		frame.getContentPane().add(etichettaPrezzo);
		etichettaPrezzo.setVisible(false);
		
		boxMetodoPagamento = new JComboBox();
		boxMetodoPagamento.setBounds(191, 255, 127, 22);
		boxMetodoPagamento.addItem("Contanti");
		boxMetodoPagamento.addItem("Carta");
		frame.getContentPane().add(boxMetodoPagamento);
		boxMetodoPagamento.setVisible(false);
		
		pagamento = new JButton("Conferma pagamento");
		pagamento.setBounds(191, 303, 157, 25);
		frame.getContentPane().add(pagamento);
		pagamento.setVisible(false);
		
		etichettaPagamento = new JLabel("Metodo di pagamento");
		etichettaPagamento.setBounds(38, 258, 155, 16);
		frame.getContentPane().add(etichettaPagamento);
		etichettaPagamento.setVisible(false);
		
		bottoneAnnulla = new JButton("Annulla transazione");
		bottoneAnnulla.setBounds(355, 303, 155, 25);
		frame.getContentPane().add(bottoneAnnulla);
		
		frame.setVisible(true);


		//metodo che gestisce la pressione sul tasto "verifica disponibilità". Viene controllato nel database se è disponibile il gioco
		//nel formato desiderato. In caso affermativo compare il tasto per la conferma del pagamento
		BottoneGioco.addActionListener(new ActionListener() {  
		       public void actionPerformed( ActionEvent e ) {
		    	   nomeGioco = campoNomeGioco.getText();
		    	   if(BottoneGiocoNuovo.isSelected()) nuovo=true;
		    	   else nuovo=false;
		    	   if(cm.verificaDisponibilita(nomeGioco, nuovo))campoPrezzo.setText(Double.toString(cm.trovaprezzo(nomeGioco, nuovo))+"€");
		    	   else campoPrezzo.setText("Videogioco non disponibile");
		    	   if(campoPrezzo.getText().equals("Videogioco non disponibile")) metodiPagamento(false);
		    	   else metodiPagamento(true);//rendo visibili i bottoni per la conferma del pagamento
		       }
		   });
		//Gestione del tasto "Annulla"
		bottoneAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
			}
		});
		//Gestione del pagamento e quindi della definitiva vendita (con aggiornamento del database)
		pagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(boxMetodoPagamento.getSelectedItem()=="Contanti") {
					String dialog = "Conferma avvenuto pagamento in contanti";
					JOptionPane.showMessageDialog(new JFrame(),dialog,"Conferma pagamento",JOptionPane.INFORMATION_MESSAGE);
					frame.setVisible(false);
				}else {
					String dialog = Finto_pagamento_carta.pagamentoCarta();
					JOptionPane.showMessageDialog(new JFrame(),dialog,"Conferma pagamento",JOptionPane.INFORMATION_MESSAGE);
					frame.setVisible(false);
				}
		    	cm.vendita(nomeGioco, nuovo);
			}
		});
	}
	
	//Metodo che gestisce la visualizzazioni delle componente del frame, per quando viene schiacciato il pulsante
	//verifica disponibilità.
	public void metodiPagamento(boolean risultatoRicerca) {
		if(risultatoRicerca) {
		campoPrezzo.setVisible(true);
		etichettaPrezzo.setVisible(true);
		boxMetodoPagamento.setVisible(true);
		pagamento.setVisible(true);
		etichettaPagamento.setVisible(true);
		} else {
			campoPrezzo.setVisible(true);
			etichettaPrezzo.setVisible(true);
			boxMetodoPagamento.setVisible(false);
			pagamento.setVisible(false);
			etichettaPagamento.setVisible(false);
		}
	}
	
	
}
