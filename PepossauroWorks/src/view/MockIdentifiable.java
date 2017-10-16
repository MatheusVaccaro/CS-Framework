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

	public Map<String, String> getComplexRepresentation() {
		Map<String, String> complexRep = new HashMap<String, String>();
		complexRep.put("id", id.toString());
		complexRep.put("data", "" + mockData);
		
		return complexRep;
	}
}
