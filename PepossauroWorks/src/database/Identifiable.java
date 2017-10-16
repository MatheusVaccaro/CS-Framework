package database;

import java.util.Map;

public abstract class Identifiable {
	protected Object id;
	
	public Object getId() {	return id; }
	
	public String getSimpleRepresentation() {
		return id.toString();
	}
	
	public abstract Entry[] getComplexRepresentation();
	
	public class Entry {
        private String label;
        private String data;

        public Entry(String label, String data) {
            this.label = label;
            this.data = data;
        }

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}
    }
}
