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
	
	public Map<String, String> getMapRepresentation() {
		Map<String, String> dicRep = new HashMap<String, String>();
		dicRep.put("id", id.toString());
		dicRep.put("mockData", "" + mockData);
		
		return dicRep;
	}
	
	@Override
	public void updateWithDic(Map<String, String> dic) throws Exception {
		Object oldId = id;
		int oldMockData = mockData;
		
		try {
			this.id = dic.get("id");
			this.mockData = Integer.parseInt(dic.get("mockData"));			
		} catch(Exception e) {
			this.id = oldId;
			this.mockData = oldMockData;
			throw e;
		}
	}
	
	public Identifiable copy() {
		MockIdentifiable copy = new MockIdentifiable((String)this.getId());
		copy.mockData = this.mockData;
		return copy;
	}
	
	public String toString() {
		return id + " " + mockData;
	}

}
