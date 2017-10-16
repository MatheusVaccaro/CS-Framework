package view;

import javafx.stage.*;
import javafx.util.StringConverter;
import view.TableViewController.Entry;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.*;

public class EntryCreationBox {
	
	public static void display() {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Create Entry");
		window.setMinWidth(300);
		
		Label label = new Label();
		label.setText("Create a new entry");
		Button button = new Button("Create");
		button.setOnAction(e -> window.close());
		
        TableView<Entry> tableView = new TableView<>();
        TableColumn<Entry, String> labelColumn = new TableColumn<>("Label");
        TableColumn<Entry, String> dataColumn = new TableColumn<>("Data");

        labelColumn.setMinWidth(150);
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
        
        dataColumn.setMinWidth(150);
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("entry"));
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
        
        tableView.getColumns().addAll(labelColumn, dataColumn);
        
        tableView.setEditable(true);
        dataColumn.setEditable(true);
        
        
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, tableView, button);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
