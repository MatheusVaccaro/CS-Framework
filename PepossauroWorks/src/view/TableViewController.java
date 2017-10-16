package view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import database.Identifiable;
import javafx.collections.FXCollections;
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
	@FXML
	private TableView tableView;
	@FXML
	private TableColumn<Identifiable, Identifiable> labelColumn;
	@FXML
	private TableColumn<Identifiable, Identifiable> dataColumn;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;
	
	public TableViewController() { }

	@FXML
	private void initialize() {
		
		labelColumn.setCellFactory(column -> {
		    return new TableCell<Identifiable, Identifiable>() {
		        @Override
		        protected void updateItem(Identifiable item, boolean empty) {
		            super.updateItem(item, empty);

		            super.updateItem(item, empty);
                    if (item != null) {
                        item.getComplexRepresentation();
                    } else {
                        setText("");
                    }
		        }
		    };
		});
		
		
		labelColumn.setCellValueFactory(new PropertyValueFactory<>("label"));
		dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
		dataColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		tableView.setItems(FXCollections.observableArrayList(pessoas));
		tableView.setEditable(true);
		dataColumn.setEditable(true);
	
	}

	public void setData(Map<String, String> dic) {
		tableView.setItems(value);
	}
	
	public static class Entry {

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
