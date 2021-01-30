package manager_dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import manager_filiale.ManagerModel;

public class DialogNuoveCopie {
	private static Dialog d;
	private String s1="Scrivi il nome gioco del gioco";
	private String s2="Scrivi il l' aggiunta";
	JTextField nomegiocoText = new JTextField(s1);
	JTextField quantitaText = new JTextField(s2); 
	JButton bprocedi = new JButton("Procedi");
	ManagerModel mm;
		 
	public DialogNuoveCopie(ManagerModel m) {
		   JFrame f= new JFrame();  
		   mm = m;
		   nomegiocoText.setEditable(true); 
		   d = new JDialog(f , "Aggiungi copie di un gioco", true);
		   d.setSize(400, 120);
		   d.setLocation(450, 240);
				
		   bprocedi.addActionListener(new ActionListener()  
		   {  
		       public void actionPerformed( ActionEvent e )  
		       {
		    	   	try {
		    	   		// verifico che il manager abbia effettivamente inserito i dati e che siano accettabili
		    	   		if ((!nomegiocoText.getText().equals("") || !nomegiocoText.getText().equals(s1) ||
		    	   				!quantitaText.getText().equals("") || !quantitaText.getText().equals(s2)) &&
		    	   				Integer.parseInt(quantitaText.getText())>0)
		    	   		{
		    	   			mm.aggiungiquantitagiochi(nomegiocoText.getText(), true, 
		    	   					Integer.parseInt(quantitaText.getText()));
		    	   			DialogNuoveCopie.d.setVisible(false); 
		    	   		}else{
		    	   			String message="Devi inserire i dati correttamente";
		    	   			JOptionPane.showMessageDialog(new JFrame(),message,"Errore",JOptionPane.ERROR_MESSAGE);
		    	   		}
		    	 } catch (NumberFormatException e1) {
		    		 System.out.println("Devi inserire i dati correttamente");
		    	 }
		       }  
		   }); 
		   d.add(nomegiocoText,BorderLayout.WEST); 
		   d.add(quantitaText,BorderLayout.EAST);
		   d.add(bprocedi,BorderLayout.SOUTH);		
		   d.setVisible(true); 
	}  
}
