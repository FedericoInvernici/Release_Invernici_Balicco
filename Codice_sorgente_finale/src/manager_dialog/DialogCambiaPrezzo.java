package manager_dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import manager_filiale.ManagerModel;



//creo jdialog per il metodo oredina
public class DialogCambiaPrezzo
{ 
	private static Dialog d;
	private String s1="Scrivi il gioco da ordinare";
	private String s2="Scegli il prezzo da dargli";
	JTextField nomegiocoText = new JTextField(s1);
	JTextField prezzogiocoText = new JTextField(s2);
	JButton bcambianuovo = new JButton("Cambia prezzo nuovo");
	JButton bcambiausato = new JButton("Cambia prezzo usato");
	ManagerModel mm;
			 
	public DialogCambiaPrezzo(ManagerModel m) {
		JFrame f= new JFrame();
		mm = m;
		nomegiocoText.setEditable(true);
		prezzogiocoText.setEditable(true);
		d = new JDialog(f , "Cambia il prezzo a un gioco", true); 
		d.setSize(450, 120);
		d.setLocation(400, 240);
				    
						
		//bottone per i giochi nouvi
		bcambianuovo.addActionListener(new ActionListener()  
		{  
			public void actionPerformed( ActionEvent e )  
			{  
				try{
					// verifico che il i dati siano inseriti e che il prezzo sia accettabile
					if ((!nomegiocoText.getText().equals("") || !nomegiocoText.getText().equals(s1) ||
						!prezzogiocoText.getText().equals("") || !prezzogiocoText.getText().equals(s2))&&
							Float.parseFloat(prezzogiocoText.getText())>0) 
					{
					    mm.cambiaprezzo(nomegiocoText.getText(), true, 
					    		Float.parseFloat(prezzogiocoText.getText())) ;
						DialogCambiaPrezzo.d.setVisible(false); 
					}else{
						String message="Inserisci i dati in modo corretto";
						JOptionPane.showMessageDialog(new JFrame(), message, "Errore", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e1) {
		    		 System.out.println("Devi inserire i dati correttamente");
		    	}
			}  
		}); 
		
		
		//bottone per i giochi usati
		bcambiausato.addActionListener(new ActionListener()  
		{  
			public void actionPerformed( ActionEvent e )  
			{  
				try{
					if ((!nomegiocoText.getText().equals("") || !nomegiocoText.getText().equals(s1) ||
							!prezzogiocoText.getText().equals("") || !prezzogiocoText.getText().equals(s2))
							&& Float.parseFloat(prezzogiocoText.getText())>0)
					{
						mm.cambiaprezzo(nomegiocoText.getText(), false, 
				    		Float.parseFloat(prezzogiocoText.getText())) ;
						DialogCambiaPrezzo.d.setVisible(false); 
					}else{
						String message="Inserisci i dati in modo corretto";
						JOptionPane.showMessageDialog(new JFrame(), message, "Errore", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e1) {
					System.out.println("Devi inserire i dati correttamente");
				}
			}
		}); 	
					
		d.add(nomegiocoText,BorderLayout.NORTH);
		d.add(prezzogiocoText,BorderLayout.CENTER);	
		d.add(bcambianuovo,BorderLayout.WEST);
		d.add(bcambiausato,BorderLayout.EAST);
		d.setVisible(true); 
	}  
}
