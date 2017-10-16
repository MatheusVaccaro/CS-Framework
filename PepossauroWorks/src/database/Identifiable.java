package database;

import java.util.Map;

public abstract class Identifiable {
	protected Object id;
	
	public Object getId() {	return id; }
	
	public String getSimpleRepresentation() {
		return id.toString();
	}
	
	public abstract Map<String, String> getComplexRepresentation();
}
