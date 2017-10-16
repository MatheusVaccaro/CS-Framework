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
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;

public class Controller {

	private Pepoview app;
	
	@FXML
	private ListView<Identifiable> listView;
	@FXML
	private Button createEntry;
	
	 public Controller(Pepoview app) {
    	this.app = app;
    }

	@FXML
	private void initialize() {
		
		ObservableList<Identifiable> items = app.getIdentifiableList();
        
		listView.setCellFactory(new Callback<ListView<Identifiable>, ListCell<Identifiable>>() {
			@Override
			public ListCell<Identifiable> call(ListView<Identifiable> param) {
				ListCell<Identifiable> cell = new ListCell<Identifiable>() {
                    @Override
                    protected void updateItem(Identifiable item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getSimpleRepresentation());
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
			}
        });
		listView.setItems(items);
		listView.refresh();

		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Identifiable>() {
			@Override
			public void changed(ObservableValue<? extends Identifiable> ov, Identifiable oldValue, Identifiable newValue) {
				selectionChanged(ov, oldValue, newValue);				
			}
		});
		
		listView.getSelectionModel().selectFirst();
		
		createEntry.setOnAction(e -> EntryCreationBox.display());
	}

	private void selectionChanged(ObservableValue<? extends Identifiable> observable, Identifiable oldValue, Identifiable newValue) {
		app.getTableViewController().setCurrentData(newValue);
	}
}

