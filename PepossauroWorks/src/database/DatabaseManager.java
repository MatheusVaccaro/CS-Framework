package database;

import java.util.Collection;

public abstract class DatabaseManager<T extends Identifiable> {
	
	public abstract Collection<T> retrieveAll();
	
	public abstract T create(T data);
	
	public abstract T retrieve(Object id);
	public T retrieve(T data) {	
		return retrieve(data.getId()); 
	}
	
	public abstract T retriveAllExternalData();
	
	public abstract T update(T data, Object id);
	public T update(T data) { 
		return update(data, data.getId());
	}
	
	public abstract T delete(Object id);
	public T delete(T data) {
		return delete(data.getId());
	}
	
}
