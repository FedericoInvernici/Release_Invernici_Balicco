package commesso;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CommessoView extends JFrame {
	
    private JTextField nomegiocoText     	= new JTextField(30);
    private JButton    acquistanuovoButton 	= new JButton("Acquista gioco"); 
    private JButton    preordinaButton   	= new JButton("Preordina gioco"); 
    private JButton    usatoButton    	 	= new JButton("Ritira usato");
    private JButton    prezzoButton      	= new JButton("Trova il prezzo"); 
    private JButton    iscriviButton     	= new JButton("Iscrivi un cliente");
	private CommessoModel cmodel;
    
	public CommessoView (CommessoModel c)
	{
		cmodel = c;
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout()); 
        content.add(nomegiocoText);
        content.add(acquistanuovoButton);
        content.add(preordinaButton);
        content.add(usatoButton);
        content.add(prezzoButton);
        content.add(iscriviButton);
        this.setContentPane(content);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(400, 200, 430, 200);
	}
	
	
	void addAcqustanuovoListener(ActionListener m) {
		acquistanuovoButton.addActionListener(m);
    }
	
	void addPreordinaListener(ActionListener m) {
		preordinaButton.addActionListener(m);
    }
	
	void addUsatoListener(ActionListener m) {
		usatoButton.addActionListener(m);
    }
	
	void addPrezzoListener(ActionListener m) {
		prezzoButton.addActionListener(m);
    }
	
	void addIscriviListener(ActionListener m) {
		iscriviButton.addActionListener(m);
    }
	
	
	
	
	
	public String getNomegiocoText() {
		return nomegiocoText.getText();
	}
	
	public void setNomegiocoText(String s) {
		nomegiocoText.setText(s);
	}
	
}
