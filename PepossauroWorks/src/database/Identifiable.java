package database;

public abstract class Identifiable {
	protected Object id;
	
	public Object getId() {	return id; }
	
	public String getSimpleRepresentation() {
		return id.toString();
	}
}
