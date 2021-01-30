package commesso;

import java.util.Date;

import utility.GestoreJson;
import utility.RicercaInterpolata;


public class Main_commesso {

	
	public static void main(String[] args) {
		CommessoModel cmodel = new CommessoModel();
		CommessoView cview = new CommessoView(cmodel);
		CommessoControl ccontrol = new CommessoControl(cview, cmodel);
		cview.setVisible(true);
		System.out.println("esecuzione");		
	}
	
}
