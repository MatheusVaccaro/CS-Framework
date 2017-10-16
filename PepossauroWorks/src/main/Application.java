package main;

import database.Pepofile;
import view.Pepoview;

public class Application {
	public static void main(String[] args) {		
		Pepofile.db = null;
		
//		Class.forName("String").getConstructors()[0].newInstance();
		
		Pepoview.main(args);
	}
}