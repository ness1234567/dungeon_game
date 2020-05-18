package unsw.dungeon;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class GameOverScreen {

    private Stage stage;
    private String title;
    private Scene scene;
    private GameOverController controller;
    
    public GameOverScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "German Torture Dungeon";
        
        this.controller = new GameOverController();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOverScreen.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    }
    
    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void setTitle(String str) {
    	controller.setText(str);
    }
    
    public GameOverController getController() {
        return controller;
    }
}
