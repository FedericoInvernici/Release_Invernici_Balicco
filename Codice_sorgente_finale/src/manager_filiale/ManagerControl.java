package manager_filiale;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import manager_dialog.DialogAggiungiPrenotabili;
import manager_dialog.DialogCambiaPrezzo;
import manager_dialog.DialogNuoveCopie;
import manager_dialog.DialogNuovoGioco;
import manager_dialog.DialogOrd;
import manager_dialog.DialogReport;


public class ManagerControl {

	private ManagerView mview;
	private ManagerModel mmodel;
	
	public ManagerControl(ManagerView mv, ManagerModel mm) {
		mview=mv;
		mmodel=mm;
		mview.addOrdinaListener(new ordina());
		mview.addCambiaPrezzoListener(new cambiaprezzo());
		mview.addReportListener(new report());
		mview.addNuovoGiocoListener(new nuovogioco());
		mview.addNuovaCopiaListener(new nuovacopia());
		mview.addNuovoGiocoPrenotabileListener(new nuovogiocoprenotabile());
	}
	
	
	//metodi dei button
	class ordina implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	//new DialogOrdina(mmodel);
        	new DialogOrd(mmodel);
        }
	}
	
	class cambiaprezzo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	new DialogCambiaPrezzo(mmodel);
        	
        }
	}
	
	class report implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	new DialogReport(mmodel);
        }
	}
	
	class nuovogioco implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	new DialogNuovoGioco(mmodel);
        	mmodel.ordinaArrayGiochi();
        }
	}
	
	class nuovacopia implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	new DialogNuoveCopie(mmodel);
        }
	}
	
	class nuovogiocoprenotabile implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	new DialogAggiungiPrenotabili(mmodel);
        }
	}
	
	
}