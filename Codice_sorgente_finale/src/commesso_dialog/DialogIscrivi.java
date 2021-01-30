package commesso_dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import commesso.CommessoModel;
import manager_dialog.DialogCambiaPrezzo;

public class DialogIscrivi
{ 
	private static Dialog d;
	private String s1="Scrivi l' email";
	private String s2="Scrivi il nome";
	private String s3="Scrivi il cognome";
	JTextField emailText = new JTextField(s1);
	JTextField nomeText = new JTextField(s2);
	JTextField cognomeText = new JTextField(s3);
	JButton bprocedi = new JButton("Iscrivilo");
	CommessoModel cm;
		 
	public DialogIscrivi(CommessoModel m) {
		   JFrame f= new JFrame();  
		   cm = m;
		   emailText.setEditable(true);
		   nomeText.setEditable(true);
		   cognomeText.setEditable(true);
		   d = new JDialog(f , "Iscrivi un cliente", true);
		   d.setSize(400, 120);
		   d.setLocation(450, 240);
				
		   bprocedi.addActionListener(new ActionListener()  
		   {  
		       public void actionPerformed( ActionEvent e )  
		       {
		    	   	try {
		    	   		if (!emailText.getText().equals("") || !emailText.getText().equals(s1) ||
		    	   				!nomeText.getText().equals("") || !nomeText.getText().equals(s2) ||
		    	   				!cognomeText.getText().equals("") || !cognomeText.getText().equals(s3) ) 
		    	   		{
		    	   			cm.iscrivi(emailText.getText(), nomeText.getText(), cognomeText.getText()); 
							DialogIscrivi.d.setVisible(false); 
		    	   		}else{
		    	   			String message="Devi inserire i dati correttamente";
		    	   			JOptionPane.showMessageDialog(new JFrame(),message,"Errore",JOptionPane.ERROR_MESSAGE);
		    	   		}
		    	 } catch (NumberFormatException e1) {
		    		 System.out.println("Devi inserire i dati correttamente");
		    	 }
		       }  
		   }); 
		   d.add(emailText,BorderLayout.NORTH);
		   d.add(nomeText,BorderLayout.WEST);
		   d.add(cognomeText,BorderLayout.EAST);
		   d.add(bprocedi,BorderLayout.SOUTH);		
		   d.setVisible(true); 
	}  
	
	
}