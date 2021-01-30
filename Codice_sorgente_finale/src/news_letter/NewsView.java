package news_letter;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import commesso.CommessoModel;

public class NewsView extends JFrame {

	JTextArea textArea 					 = new JTextArea(10, 20);
	JScrollPane scroll 					 = new JScrollPane (textArea);
    private JButton    sendButton	 	 = new JButton("invia newsletter");
	private NewsModel nmodel;
    
	public NewsView (NewsModel n)
	{
		nmodel = n;
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		content.add(scroll);
        content.add(sendButton);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
        this.setContentPane(content);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(400, 200, 400, 250);
	}
	
	
	void addAcqustaListener(ActionListener m) { 
		sendButton.addActionListener(m); 
    }
	
	
}
