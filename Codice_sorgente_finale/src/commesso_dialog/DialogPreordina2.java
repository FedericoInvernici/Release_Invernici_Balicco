package commesso_dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import commesso.CommessoModel;
import manager_dialog.DialogCambiaPrezzo;

public class DialogPreordina2 {
	private static Dialog d;
	private String s1="Scrivi il nome cliente";
	private String s2="Scrivi la sua email";
	JTextField nomeText = new JTextField(s1);
	JTextField emailText = new JTextField(s2);
	JButton bprocedi = new JButton("Procedi");
	CommessoModel cm;
	String nomeGioco;
		 
	public DialogPreordina2(CommessoModel m, String nomeGioco) {
		   JFrame f= new JFrame();  
		   this.nomeGioco=nomeGioco;
		   cm = m;
		   nomeText.setEditable(true);
		   emailText.setEditable(true);
		   d = new JDialog(f , "Dettagli clienta per conferma prenotazione", true);
		   d.setSize(400, 120);
		   d.setLocation(450, 240);
				
		   bprocedi.addActionListener(new ActionListener()  
		   {  
		       public void actionPerformed( ActionEvent e )  
		       {
		    	   	if (!nomeText.getText().equals("") || !nomeText.getText().equals(s1) ||
		    	   			!emailText.getText().equals("") || !emailText.getText().equals(s2)) 
		    	   	{
		    	   		cm.aggiungiPreordine(nomeGioco, emailText.getText(), nomeText.getText());
		    	   		JOptionPane.showMessageDialog(new JFrame(),"Prenotazione confermata","Conferma",1);
						DialogPreordina2.d.setVisible(false); 
		    	   	}else{
		    	   		String message="Devi inserire i dati correttamente1";
		    	   		JOptionPane.showMessageDialog(new JFrame(),message,"Errore",JOptionPane.ERROR_MESSAGE);
		    	   	}
		       }  
		   }); 
		   d.add(nomeText,BorderLayout.NORTH);
		   d.add(emailText,BorderLayout.CENTER);
		   d.add(bprocedi,BorderLayout.SOUTH);		
		   d.setVisible(true); 
	}  
	
}
