package news_letter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class NewsControl {

	private NewsView nview;
	private NewsModel nmodel;
	
	public NewsControl(NewsView nv, NewsModel nm) {
		nview=nv;
		nmodel=nm;
		nview.addAcqustaListener(new spedisci());
	}
	
	
	//metodi dei button
	class spedisci implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
        	nmodel.spedisci(nview.textArea.getText());
        }
	}
	
	
}