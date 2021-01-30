package commesso_dialog;

import java.text.SimpleDateFormat;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import commesso.CommessoModel;
import manager_dialog.DialogCambiaPrezzo;

public class DialogPreordina1 {
	private static Dialog d;
	private String s1="Scrivi il nome gioco";
	//private String s2="Scrivi il prezzo";
	//private String s3="gg/mm/yyyy";
	JTextField giocoText = new JTextField(s1);
	//JTextField prezzoText = new JTextField(s2);
	//JTextField dataText = new JTextField(s3);
	JButton bprocedi = new JButton("Procedi");
	String nomeGioco;
	CommessoModel cm;
		 
	public DialogPreordina1(CommessoModel m) {
		   JFrame f= new JFrame();  
		   cm = m;
		   giocoText.setEditable(true);
		   d = new JDialog(f , "Scrivi il gioco da prenotare", true);
		   d.setSize(400, 120);
		   d.setLocation(450, 240);
				
		   bprocedi.addActionListener(new ActionListener()  
		   {  
		       public void actionPerformed( ActionEvent e )  
		       {
		    	   	try {
		    	   		nomeGioco=giocoText.getText();
		    	   		if(!giocoText.getText().equals("")&&cm.verificaDisponibilitaPreo(nomeGioco)) {
		    	   			String conferma="Gioco presente nel database. \n Il costo del gioco è: " + cm.trovaprezzo(nomeGioco, true) + " €\nPagamento per caparra: "+ (int)cm.trovaprezzo(nomeGioco, true)*0.2 +" €\nIl cliente accetta l'importo?";
		    	    		int a = JOptionPane.showConfirmDialog(new JFrame(),conferma,"Conferma", JOptionPane.YES_NO_OPTION); //Se viene schiacciato il tasto Sì, si ritorna il valore 0
		    	        	if (a==0) {
		    	        		DialogPreordina1.d.setVisible(false); 
		    	        		new DialogPreordina2(cm, nomeGioco);
		    	        	}
		    	   			
		    	   		}else {
		    	   			JOptionPane.showMessageDialog(new JFrame(),"Gioco non disponibile","Errore",0);
		    	   		}
		    	   	}
		    	   	catch (Exception e2) {
		    	   		System.out.println("Devi inserire i dati correttamente");
		            }
		    	 
		       }  
		   }); 
		   d.add(giocoText,BorderLayout.NORTH);
		  d.add(bprocedi,BorderLayout.SOUTH);		
		   d.setVisible(true); 
	}  
	
	
}
