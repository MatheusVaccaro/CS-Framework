package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.cell.TextFieldListCell;

public class Controller {

	@FXML
	private ListView<String> listView;

	@FXML
	private ListView<String> rightListView;

	public Controller() {
	}

	@FXML
	private void initialize() {

		ObservableList<String> items = FXCollections.observableArrayList("Single", "Double", "Suite", "Family App");
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

