package view;

import java.util.HashMap;
import java.util.Map;

import database.Identifiable;

public class MockIdentifiable extends Identifiable {
	
	private int mockData;
	
	public MockIdentifiable(String id) {
		this.id = id;
		this.mockData = id.hashCode() % 100;
	}

	public Entry[] getComplexRepresentation() {
//		Map<String, String> complexRep = new HashMap<String, String>();
//		complexRep.put("id", id.toString());
//		complexRep.put("data", "" + mockData);
		
		Entry[] entries = new Entry[2];
		entries[0] = new Entry("id", id.toString());
		entries[1] = new Entry("data", "" + mockData);
		
		return entries;
	}
}
