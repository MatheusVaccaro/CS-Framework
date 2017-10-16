package view;

import java.io.IOException;

import database.DatabaseManager;
import database.Identifiable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Pepofile;

public class Pepoview extends Application {

    private Stage primaryStage;
    private SplitPane rootLayout;
    
    private Controller controller;
    private TableViewController tableViewController;
    
    private DatabaseManager db = Pepofile.db;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PePoSsAuRo EnTeRpRiSe");
        this.primaryStage.getIcons().add(new Image("logo.png"));
        this.primaryStage.setOnCloseRequest((event) -> { Platform.exit(); System.exit(0); });
        
        initRootLayout();
        showRightPane();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            System.out.println("awe");
            loader.setLocation(Pepoview.class.getResource("MainWindow.fxml"));
            loader.setControllerFactory(className -> new Controller(this));            
            System.out.println("owa");
            rootLayout = (SplitPane) loader.load();

            System.out.println("fk u sysout");
            
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRightPane() {
    	 try {
             // Load root layout from fxml file.
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(Pepoview.class.getResource("TableView.fxml"));
             loader.setControllerFactory(className -> new TableViewController());    
             System.out.println("--1--");
             Object anchorPane = loader.load();
             System.out.println("--2--");
             ((AnchorPane) rootLayout.getItems().get(1)).getChildren().add((AnchorPane) anchorPane);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
    
    /**
     * Shows the person overview inside the root layout.
     */
    public void showMainWindow() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Pepoview.class.getResource("MainWindow.fxml"));            
//            loader.setControllerFactory(className -> new StreamController(collector));            
            AnchorPane mainWindow = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
//            rootLayout.setCenter(mainWindow);
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
    
    public static void main(String[] args) {
        launch(args);
    }
    
}