package mock;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import database.DatabaseManager;
import database.Identifiable;

public class MockDB extends DatabaseManager<Identifiable> {
	
	private Set<Identifiable> items;
	
	public MockDB() {
		items = new LinkedHashSet<Identifiable>();
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
		for(Identifiable identifiable: items) {
			if (identifiable.getId().equals(data.getId())) {
				return null;
			}
		}
		items.add(data);
		
		return data;
	}

	@Override
	public Identifiable retrieve(Object id) {
		for(Identifiable obj: items) {
			if (obj.getId().equals(id)) {
				return obj;
			}
		}
		return null;
	}

	@Override
	public Identifiable update(Identifiable data) {
		System.out.println(items.remove(data));
		items.add(data);
		
		return data;
	}

	@Override
	public Identifiable delete(Object id) {
		Identifiable objToRemove = null;
		for(Identifiable obj: items) {
			if (obj.getId().equals(id)) {
				objToRemove = obj;
				break;
			}
		}
		System.out.println("deleting " + objToRemove);
		items.remove(objToRemove);
		return objToRemove;
	}
}
