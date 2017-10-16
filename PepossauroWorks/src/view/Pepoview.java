package view;

import java.io.IOException;

import database.DatabaseManager;
import database.Identifiable;
import database.Pepofile;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Pepoview extends Application {

    private Stage primaryStage;
    private SplitPane rootLayout;
    
    private Controller controller;
    
    private DatabaseManager db = Pepofile.db;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PePoSsAuRo EnTeRpRiSe");
        this.primaryStage.getIcons().add(new Image("logo.png"));
        this.primaryStage.setOnCloseRequest((event) -> { Platform.exit(); System.exit(0); });
        
        initRootLayout();
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
            loader.setControllerFactory(className -> new Controller());            
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
    
    public static void main(String[] args) {
        launch(args);
    }
    
}