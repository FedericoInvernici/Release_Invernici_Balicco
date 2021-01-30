package manager_dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.w3c.dom.events.EventException;

import manager_filiale.ManagerModel;


//creo jdialog per il metodo nuovogioco
public class DialogNuovoGioco
{ 
	private static Dialog d;
	private String s1="Scrivi il nuovo gioco";
	private String s2="Scrivi il prezzo per gioco nuovo";
	private String s3="Scrivi il prezzo per gioco usato";
	JTextField nomegiocoText = new JTextField(s1);
	JTextField nuovoText = new JTextField(s2);
	JTextField usatoText = new JTextField(s3);
	JButton bprocedi = new JButton("Procedi");
	ManagerModel mm;
		 
	public DialogNuovoGioco(ManagerModel m) {
		   JFrame f= new JFrame();  
		   mm = m;
		   nomegiocoText.setEditable(true); 
		   d = new JDialog(f , "Aggiungi un gioco al catalogo", true);
		   d.setSize(400, 120);
		   d.setLocation(450, 240);
				
		   bprocedi.addActionListener(new ActionListener()  
		   {  
		       public void actionPerformed( ActionEvent e )  
		       {
		    	   	try {
		    	   		// verifico che il manager abbia effettivamente inserito i dati di un nuovo gioco e che siano accettabili
		    	   		if ((!nomegiocoText.getText().equals("") || !nomegiocoText.getText().equals(s1) ||
		    	   				!nuovoText.getText().equals("") || !nuovoText.getText().equals(s2) ||
		    	   				!usatoText.getText().equals("") || !usatoText.getText().equals(s3)) &&
		    	   				(Float.parseFloat(nuovoText.getText())>0&&Float.parseFloat(usatoText.getText())>0)) 
		    	   		{
		    	   			mm.aggiungigiochi(nomegiocoText.getText(), 		//aggiunge un gioco nuovo
		    	   					Float.parseFloat(nuovoText.getText()), 
		    	   					Float.parseFloat(usatoText.getText()));
		    	   			DialogNuovoGioco.d.setVisible(false); 
		    	   		}else{
		    	   			String message="Devi inserire i dati correttamente";
		    	   			JOptionPane.showMessageDialog(new JFrame(),message,"Errore",JOptionPane.ERROR_MESSAGE);
		    	   		}
		    	 } catch (NumberFormatException e1) {
		    		 System.out.println("Devi inserire i dati correttamente");
		    	 }
		       }  
		   }); 
		   d.add(nomegiocoText,BorderLayout.NORTH);
		   d.add(nuovoText,BorderLayout.WEST);
		   d.add(usatoText,BorderLayout.EAST);
		   d.add(bprocedi,BorderLayout.SOUTH);		
		   d.setVisible(true); 
	}  
	
	
}
	
