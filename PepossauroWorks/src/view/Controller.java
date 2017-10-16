package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Controller {
	
    @FXML
    private ListView listView;
    
    public Controller() {
//      
      System.out.println(listView);
      ListView<String> list = new ListView<String>();
      ObservableList<String> items = FXCollections.observableArrayList (
          "Single", "Double", "Suite", "Family App");
      list.setItems(items);
      listView.setItems( items );
      listView.refresh();
      System.out.println(listView);
    }
}
