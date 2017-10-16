package database;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import view.MockIdentifiable;

public class MockDB extends DatabaseManager<Identifiable> {
	
	private Set<Identifiable> items;
	
	public MockDB() {
		items = new HashSet<Identifiable>();
		items.add(new MockIdentifiable("Mock1"));
		items.add(new MockIdentifiable("Mock2"));
		items.add(new MockIdentifiable("Mock3"));
	}

	@Override
	public Collection<Identifiable> retrieveAll() {
		return items;
	}

	@Override
	public Identifiable create(Identifiable data) {
		return data;
	}

	@Override
	public Identifiable retrieve(Object id) {
		for(Identifiable obj: items) {
			if (obj.id.equals(id)) {
				return obj;
			}
		}
		return null;
	}

	@Override
	public Identifiable update(Identifiable data, Object id) {
		MockIdentifiable mock = new MockIdentifiable((String)data.id);
		items.remove(id);
		items.add(mock);
		
		return mock;
	}

	@Override
	public Identifiable delete(Object id) {
		Identifiable objToRemove = null;
		for(Identifiable obj: items) {
			if (obj.id.equals(id)) {
				objToRemove = obj;
				break;
			}
		}
		
		items.remove(objToRemove);
		return objToRemove;
	}
}
