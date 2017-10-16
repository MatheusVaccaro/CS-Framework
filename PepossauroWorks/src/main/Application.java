package main;

import view.MockIdentifiable;
import view.Pepoview;

public class Application {
	public static void main(String[] args) {		
		Pepofile.db = null;
		Pepofile.identifiable = new MockIdentifiable("mock");
		
		Pepoview.main(args);
	}
}