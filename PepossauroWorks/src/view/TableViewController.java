package view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import database.DatabaseManager;
import database.Identifiable;
import database.MockDB;
import database.Identifiable.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import javafx.scene.paint.Paint;
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
		
		
		labelColumn.setCellValueFactory(new PropertyValueFactory<>("label"));
		dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
		dataColumn.setCellFactory(column -> {
		    return new TextFieldTableCell<Entry, String>() {
		        @Override
				public void updateItem(String item, boolean empty) {
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
		tableView.setEditable(true);
		dataColumn.setEditable(true);
		
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if (currentData != null) {
		    		Identifiable oldData = currentData;
		    		setData(null);
		    		app.tableCellDidDelete(oldData);
		    	}
		    }
		});
	}

	public void setData(Identifiable data) {
		currentData = data;
		
		ObservableList<Entry> entries = null;
		if (currentData != null) {
			entries = FXCollections.observableArrayList(data.getComplexRepresentation());
		} else {
			entries = FXCollections.observableArrayList(new Entry[0]);
		}
		
		tableView.setItems(entries);
	}
}
