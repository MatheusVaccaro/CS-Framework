package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

import database.DatabaseManager;
import database.Identifiable;
import database.MockDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.cell.TextFieldListCell;

public class Controller {

	private Pepoview app;
	private DatabaseManager<Identifiable> db;
	
	@FXML
	private ListView<String> listView;

	@FXML
	private ListView<String> rightListView;

	 public Controller(Pepoview app) {
    	this.app = app;
    	this.db = app.getDB();
    	if (db == null) {
    		db = new MockDB();
    	}
    }

	@FXML
	private void initialize() {

		ArrayList<String> simpleItems = new ArrayList<String>();
    	for(Object obj: db.retrieveAll().stream().map(i -> i.getSimpleRepresentation()).toArray()) {
    		simpleItems.add((String)obj);
    	}

        ObservableList<String> items = FXCollections.observableArrayList(simpleItems);
		listView.setItems(items);
		listView.refresh();
		System.out.println(listView);

		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
				selectionChanged(ov, oldValue, newValue);
			}
		});
		
		listView.getSelectionModel().selectFirst();

	}

	private void selectionChanged(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		// retrieve the selected row data and add it to rightListView
	}
}

