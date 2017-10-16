package view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import database.Identifiable;
import database.Identifiable.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class TableViewController {
	
	private Identifiable currentData;
	
	@FXML
	private TableView<Entry> tableView;
	@FXML
	private TableColumn<Identifiable, String> labelColumn;
	@FXML
	private TableColumn<Identifiable, String> dataColumn;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;
	
	public TableViewController() { }

	@FXML
	private void initialize() {
		
		labelColumn.setCellFactory(column -> {
		    return new TableCell<Identifiable, String>() {
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
		dataColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//		tableView.setItems(FXCollections.observableArrayList(pessoas));
		tableView.setEditable(true);
		dataColumn.setEditable(true);
	
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
