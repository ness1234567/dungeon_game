package unsw.dungeon;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class DungeonScreen {

    private Stage stage;
    private String title;
    private Scene scene;
    private DungeonController controller;
    
    public DungeonScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "German Torture Dungeon";
        
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("test.json");
        this.controller = dungeonLoader.loadController();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    }

    public void setLevel(String jsonfile) throws IOException {      
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(jsonfile);
        this.controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    }
    
    public void start() {
    	controller.startDungeon();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public DungeonController getController() {
        return controller;
    }
}
