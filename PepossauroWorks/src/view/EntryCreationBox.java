package view;

import javafx.stage.*;
import javafx.util.StringConverter;
import main.Pepofile;
import view.TableViewController.Entry;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.Identifiable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;

public class EntryCreationBox {
	
	public static void display(Identifiable identifiable, Pepoview app) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Create Entry");
		window.setMinWidth(300);
		
		Label label = new Label();
		label.setText("Create a new entry");
		
        TableView<Entry> tableView = new TableView<>();
        TableColumn<Entry, String> labelColumn = new TableColumn<>("Label");
        TableColumn<Entry, String> dataColumn = new TableColumn<>("Data");

        Map<String, String> map = identifiable.getMapRepresentation();
        ObservableList<Entry> entries = FXCollections.observableArrayList(convertMapToEntries(map));
        
        tableView.setItems(entries);
        
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
        dataColumn.setOnEditCommit((CellEditEvent<Entry, String> event) -> {
			System.out.println(event.getNewValue());
			Entry entryToEdit = ((Entry) event.getTableView().getItems().get(event.getTablePosition().getRow()));
	        entryToEdit.setData((event.getNewValue()));
	    });
        
        tableView.getColumns().addAll(labelColumn, dataColumn);
        
        tableView.setEditable(true);
        dataColumn.setEditable(true);
        
		Button button = new Button("Create");
		button.setOnAction(e -> {
			System.out.println("createdd");
			Map<String, String> edittedDataMap = convertEntriesToMap(tableView.getItems());
			for(Object obj: tableView.getItems()) {
				System.out.println(obj.toString());
			}
			try {
				identifiable.updateWithDic(edittedDataMap);		    			
				app.dialogDidCreate(identifiable);
				window.close();
			} catch (Exception exception) {
				//			exception.printStackTrace();
				final Stage dialog = new Stage();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.initOwner(app.getPrimaryStage());
				VBox dialogVbox = new VBox(20);
				dialogVbox.getChildren().add(new Text("Error: " + exception.getMessage()));
				Scene dialogScene = new Scene(dialogVbox, 500, 100);
				dialog.setScene(dialogScene);
				dialog.show();
			};
		});		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, tableView, button);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	private static Collection<Entry> convertMapToEntries(Map<String, String> map) {
		ArrayList<Entry> entries = new ArrayList<Entry>();
		
		for(String key: map.keySet()) {
			Entry entry = new Entry(key, map.get(key));
			entries.add(entry);
		}
		
		return entries;
	}
	
	private static Map<String, String> convertEntriesToMap(Collection<Entry> entries) {
		Map<String, String> map = new HashMap<String, String>();
		
		for(Entry entry: entries) {
			map.put(entry.getLabel(), entry.getData());
		}
		
		return map;
	}
}
