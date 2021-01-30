package news_letter;

import manager_filiale.ManagerControl;
import manager_filiale.ManagerModel;
import manager_filiale.ManagerView;

public class Main_news {

	public static void main(String[] args) {
		NewsModel nmodel = new NewsModel();
		NewsView nview = new NewsView(nmodel);
		NewsControl ncontrol = new NewsControl(nview, nmodel);
		nview.setVisible(true);
		System.out.println("esecuzione");
	}
}
