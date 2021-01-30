package manager_dialog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import manager_filiale.ManagerModel;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogAggiungiPrenotabili {

	private JFrame frame;
	private JTextField campoNomeGioco;
	JLabel etichettaNomeGioco;
	JLabel etichettaData;
	JComboBox giorno;
	JComboBox mese;
	JComboBox anno;
	JButton bottoneConferma;
	JButton bottoneAnnulla;
	JTextArea testoEsito;
	String nomeGioco;
	int disp;
	double prezzoUscita;
	String giornoUscita, meseUscita, annoUscita;
	ManagerModel mm;
	private JLabel etichettaDisponibilita;
	private JSpinner quantitaDisponibile;
	private JLabel etichettaPrezzo;
	private JTextField campoPrezzo;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DialogAggiungiPrenotabili window = new DialogAggiungiPrenotabili();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public DialogAggiungiPrenotabili(ManagerModel mm) {
		this.mm=mm;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 346);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		etichettaNomeGioco = new JLabel("Nuovo gioco prenotabile:");
		etichettaNomeGioco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		etichettaNomeGioco.setBounds(12, 13, 181, 16);
		frame.getContentPane().add(etichettaNomeGioco);
		
		campoNomeGioco = new JTextField();
		campoNomeGioco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoNomeGioco.setBounds(205, 11, 231, 22);
		frame.getContentPane().add(campoNomeGioco);
		campoNomeGioco.setColumns(10);
		
		etichettaData = new JLabel("Data uscita:");
		etichettaData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		etichettaData.setBounds(12, 59, 117, 16);
		frame.getContentPane().add(etichettaData);
		
		giorno = new JComboBox();
		giorno.setBounds(164, 57, 46, 22);
		frame.getContentPane().add(giorno);
		inizializzaComboBox(giorno,1,31,1);
		
		mese = new JComboBox();
		mese.setBounds(229, 57, 46, 22);
		frame.getContentPane().add(mese);
		inizializzaComboBox(mese,1,12,1);
		mese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gestioneMese();
			}
		});
		
		anno = new JComboBox();
		anno.setBounds(294, 57, 60, 22);
		frame.getContentPane().add(anno);
		inizializzaComboBox(anno,2021,2030,1);
		
		bottoneConferma = new JButton("Conferma");
		bottoneConferma.setBounds(205, 148, 97, 25);
		frame.getContentPane().add(bottoneConferma);
		
		bottoneAnnulla = new JButton("Annulla");
		bottoneAnnulla.setBounds(339, 148, 97, 25);
		frame.getContentPane().add(bottoneAnnulla);
		
		testoEsito = new JTextArea();
		testoEsito.setBounds(29, 186, 208, 75);
		frame.getContentPane().add(testoEsito);
		testoEsito.setVisible(false);
		
		etichettaDisponibilita = new JLabel("Disponibilit\u00E0 all'uscita:");
		etichettaDisponibilita.setFont(new Font("Tahoma", Font.PLAIN, 15));
		etichettaDisponibilita.setBounds(12, 104, 145, 16);
		frame.getContentPane().add(etichettaDisponibilita);
		
		SpinnerNumberModel model = new SpinnerNumberModel(0.0, 0.0, 1000 , 1.0);
		quantitaDisponibile = new JSpinner(model);
		quantitaDisponibile.setBounds(164, 102, 46, 22);
		frame.getContentPane().add(quantitaDisponibile);
		
		etichettaPrezzo = new JLabel("Prezzo:");
		etichettaPrezzo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		etichettaPrezzo.setBounds(253, 105, 56, 16);
		frame.getContentPane().add(etichettaPrezzo);
		
		campoPrezzo = new JTextField();
		campoPrezzo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		campoPrezzo.setBounds(321, 102, 116, 22);
		frame.getContentPane().add(campoPrezzo);
		campoPrezzo.setColumns(10);
		
		
		
		bottoneAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		
		bottoneConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeGioco=campoNomeGioco.getText();
				disp = (int) (double)quantitaDisponibile.getValue();
				if(!nomeGioco.equals("")&&disp!=0) {
					meseUscita=(String)mese.getSelectedItem();
					annoUscita=(String)anno.getSelectedItem();
					giornoUscita=(String)giorno.getSelectedItem();
					disp = (int) (double)quantitaDisponibile.getValue();
					try {
						prezzoUscita = Double.parseDouble(campoPrezzo.getText());
						if(mm.aggiungiGiochiPrenotabili(nomeGioco, annoUscita, meseUscita, giornoUscita, prezzoUscita, disp)) {
							testoEsito.setText("Gioco inserito correttamente:" + nomeGioco +"\nNumero copie disponibili al lancio: "+ disp +"\nPrezzo al lancio: " + prezzoUscita);
						} else { //Se non è possibile inserire il gioco, viene comunicato che i dati non sono corretti
							throw new Exception();
						}
					}catch (Exception ecc) {
						testoEsito.setText("Inserire i dati in modo corretto");
					}
										
				}else {
					testoEsito.setText("Inserire i dati in modo corretto");
				}
				testoEsito.setVisible(true);
			}
		});
	}
	
	private void inizializzaComboBox(JComboBox jcb, int iniz, int fine, int passo) {
		for(int i= iniz;i<=fine;i+=passo) {
			jcb.addItem(Integer.toString(i));
		}
	}
	
	//Controllo del mese, perchè i giorni di scelta siano conformi alla numero di giorni nei singoli mesi di ciascun mese
	private void gestioneMese() {
		meseUscita= (String) mese.getSelectedItem();
		if(meseUscita.equals("11")||meseUscita.equals("4")||meseUscita.equals("6")||meseUscita.equals("9")) {
			giorno.removeAllItems();
			inizializzaComboBox(giorno, 1, 30, 1);
		}else if(meseUscita.equals("2")) {
			giorno.removeAllItems();
			inizializzaComboBox(giorno, 1, 29, 1);
		}
	}
}
