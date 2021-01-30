package manager_filiale;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ManagerView extends JFrame {
	
	private JTextField nomegiocoText     = new JTextField(30);
	private JButton    ordinaButton 	 = new JButton("Ordina un gioco");
	private JButton    cambiaprezzoButton   = new JButton("Cambia il prezzo a gioco"); 
	private JButton    reportButton    	 = new JButton("Chiedi report");
	private JButton    nuovogiocoButton   = new JButton("Aggiungi un nuovo gioco"); 
	private JButton    nuovacopiaButton    	 = new JButton("Aggiungi una nuova copia di un gioco");
	private JButton    vendiButton    	 = new JButton("Vendi un gioco");
	private JButton 	nuovoprenotabileButton = new JButton("Aggiungi gioco prenotabile");
	private ManagerModel mmodel;
	
	public ManagerView(ManagerModel m) {
		mmodel = m;
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
        //content.add(nomegiocoText);
        content.add(ordinaButton);
        content.add(cambiaprezzoButton);
        content.add(reportButton);
        content.add(nuovogiocoButton);
        content.add(nuovacopiaButton);
        content.add(nuovoprenotabileButton);
        this.setContentPane(content);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(400, 200, 500, 200);
	}
	
	


	void addOrdinaListener(ActionListener m) {
		ordinaButton.addActionListener(m);
    }
	
	void addCambiaPrezzoListener(ActionListener m) {
		cambiaprezzoButton.addActionListener(m);
    }
	
	void addReportListener(ActionListener m) {
		reportButton.addActionListener(m);
    }
	
	void addNuovoGiocoListener(ActionListener m) {
		nuovogiocoButton.addActionListener(m);
    }
	
	void addNuovaCopiaListener(ActionListener m) {
		nuovacopiaButton.addActionListener(m);
    }
	
	void addNuovoGiocoPrenotabileListener(ActionListener m) {
		nuovoprenotabileButton.addActionListener(m);
    }
		
}





	