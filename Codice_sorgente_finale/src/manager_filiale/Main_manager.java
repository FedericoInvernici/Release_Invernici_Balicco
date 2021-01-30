package manager_filiale;

import java.util.Date;

public class Main_manager {

	private static int i;

	public static void main(String[] args) {
		ManagerModel cmodel = new ManagerModel();
		ManagerView cview = new ManagerView(cmodel);
		ManagerControl ccontrol = new ManagerControl(cview, cmodel);
		cview.setVisible(true);
		System.out.println("esecuzione");
	}
	
	
	
	
	
}
