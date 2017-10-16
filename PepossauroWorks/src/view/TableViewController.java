package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import database.DatabaseManager;
import database.Identifiable;
import database.MockDB;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.Callback;

public class TableViewController {
	
	private Identifiable currentData;
	private Pepoview app;
	
	@FXML
	private TableView<Entry> tableView;
	@FXML
	private TableColumn<Entry, String> labelColumn;
	@FXML
	private TableColumn<Entry, String> dataColumn;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;


	
	public TableViewController(Pepoview app) {
    	this.app = app;
    }

	@FXML
	private void initialize() {
		
		labelColumn.setCellValueFactory(new PropertyValueFactory<>("label"));
		labelColumn.setCellFactory(column -> {
		    return new TableCell<Entry, String>() {
		        @Override
		        protected void updateItem(String item, boolean empty) {
		            super.updateItem(item, empty);

		            super.updateItem(item, empty);
                    if (item != null) {
                        setText(item);
                    } else {
                        setText("");
                    }
		        }
		    };
		});
		
		dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
		dataColumn.setCellFactory(TextFieldTableCell.<Entry, String>forTableColumn(new StringConverter<String>() {
			@Override
			public String fromString(String string) {
				return string;
			}

			@Override
			public String toString(String object) {
				return object;
			}	
		}));
//		dataColumn.setCellFactory(column -> {
//		    return new TextFieldTableCell<Entry, String>() {
//		    	
//		        @Override
//				public void updateItem(String item, boolean empty) {
//		            super.updateItem(item, empty);
//                    if (item != null) {
//                        setText(item);
//                    } else {
//                        setText("");
//                    }
//		        }
//		     
//		    };
//		});
		tableView.setEditable(true);
		dataColumn.setEditable(true);
		dataColumn.setOnEditCommit((CellEditEvent<Entry, String> event) -> {
			System.out.println(event.getNewValue());
			Entry entryToEdit = ((Entry) event.getTableView().getItems().get(event.getTablePosition().getRow()));
	        entryToEdit.setData((event.getNewValue()));
	    });
		
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if (currentData != null) {
		    		Identifiable oldData = currentData;
		    		setCurrentData(null);
		    		app.tableCellDidDelete(oldData);
		    	}
		    }
		});
		
		editButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if (currentData != null) {
		    		Map<String, String> edittedDataMap = convertEntriesToMap(tableView.getItems());
		    		try {
		    			currentData.updateWithDic(edittedDataMap);		    			
		    			app.tableCellDidEdit(currentData);
		    		} catch (Exception exception) {
//		    			exception.printStackTrace();
		    			final Stage dialog = new Stage();
		                dialog.initModality(Modality.APPLICATION_MODAL);
		                dialog.initOwner(app.getPrimaryStage());
		                VBox dialogVbox = new VBox(20);
		                dialogVbox.getChildren().add(new Text("Error: " + exception.getMessage()));
		                Scene dialogScene = new Scene(dialogVbox, 500, 100);
		                dialog.setScene(dialogScene);
		                dialog.show();
		    		}
		    	}
		    }
		});
	}

	public void setCurrentData(Identifiable data) {
		currentData = data;
		
		ObservableList<Entry> entries = null;
		if (currentData != null) {
			entries = FXCollections.observableArrayList(convertMapToEntries(data.getMapRepresentation()));
		} else {
			entries = FXCollections.observableArrayList(new Entry[0]);
		}
		
		tableView.setItems(entries);
	}
	
	private Collection<Entry> convertMapToEntries(Map<String, String> map) {
		ArrayList<Entry> entries = new ArrayList<Entry>();
		
		for(String key: map.keySet()) {
			Entry entry = new Entry(key, map.get(key));
			entries.add(entry);
		}
		
		return entries;
	}
	
	private Map<String, String> convertEntriesToMap(Collection<Entry> entries) {
		Map<String, String> map = new HashMap<String, String>();
		
		for(Entry entry: entries) {
			map.put(entry.label, entry.data);
		}
		
		return map;
	}
	
	public class Entry {
        private String label;
        private String data;

        public Entry(String label, String data) {
            this.label = label;
            this.data = data;
        }

		public String getLabel() { return label; }
		public void setLabel(String label) { this.label = label; }

		public String getData() { return data; }
		public void setData(String data) { this.data = data; }
		
		public String toString() { 
			return label + " " + data;
		}
    }
}
