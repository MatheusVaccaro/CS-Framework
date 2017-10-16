package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Controller {
	
    @FXML
    private ListView<String> listView;
    
    public Controller() {}
    
    @FXML
    private void initialize() {

        ObservableList<String> items = FXCollections.observableArrayList (
            "Single", "Double", "Suite", "Family App");
        listView.setItems(items);
        listView.refresh();
        System.out.println(listView);
    }
}
