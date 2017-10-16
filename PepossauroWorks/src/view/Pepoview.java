package view;

import java.io.IOException;

import database.DatabaseManager;
import database.Identifiable;
import database.MockDB;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Pepofile;

public class Pepoview extends Application {

    private Stage primaryStage;
    private SplitPane rootLayout;
    
    private Controller listController;
    private TableViewController tableViewController;
    
	private DatabaseManager<Identifiable> db = Pepofile.db != null ? Pepofile.db : new MockDB();
    private ObservableList<Identifiable> identifiableList;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PePoSsAuRo EnTeRpRiSe");
        this.primaryStage.getIcons().add(new Image("logo.png"));
        this.primaryStage.setOnCloseRequest((event) -> { Platform.exit(); System.exit(0); });
        
        fetchData();
        
        initRootLayout();
        showRightPane();
        showLeftPane();
    }

	private void fetchData() {
		identifiableList = FXCollections.observableArrayList(db.retrieveAll());
	}

	/**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Pepoview.class.getResource("MainWindow.fxml"));
            rootLayout = (SplitPane) loader.load();            
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void showLeftPane() {
    	 try {
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(Pepoview.class.getResource("ListView.fxml"));
             listController = new Controller(this);
             loader.setControllerFactory(className -> listController);    
             AnchorPane anchorPane = (AnchorPane) loader.load();
             ((AnchorPane) rootLayout.getItems().get(0)).getChildren().add(anchorPane);
         } catch (IOException e) {
             e.printStackTrace();
         }
	}

    public void showRightPane() {
    	 try {
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(Pepoview.class.getResource("TableView.fxml"));
             this.tableViewController = new TableViewController(this);
             loader.setControllerFactory(className -> this.tableViewController);    
             AnchorPane anchorPane = (AnchorPane) loader.load();
             ((AnchorPane) rootLayout.getItems().get(1)).getChildren().add(anchorPane);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
	public DatabaseManager<Identifiable> getDB() {
    	return this.db;
    }
    
    public TableViewController getTableViewController() {
    	return this.tableViewController;
    }
    
    public void tableCellDidDelete(Identifiable data) {
    	identifiableList.remove(data);
		db.delete(data);		    		
    }
    
	public void tableCellDidEdit(Identifiable edittedData) {
		for(int i = 0; i < identifiableList.size(); i++){
			if (identifiableList.get(i).getId() == edittedData.getId()) {
				identifiableList.remove(i);
				identifiableList.add(i, edittedData);
				break;
			}
		}
		db.update(edittedData);		
	}
    
    public ObservableList<Identifiable> getIdentifiableList() {
    	return identifiableList;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}