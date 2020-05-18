package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button singleButton;
    
    @FXML
    private Button coopButton;

    private DungeonScreen dungeonScreen;
    private GameOverScreen gameOverScreen;

    @FXML
    public void onSinglePlayer(ActionEvent event) throws IOException {
    	dungeonScreen.setLevel("test.json");
    	dungeonScreen.getController().setGameOverScreen(gameOverScreen);
    	this.dungeonScreen.start();
    }

    @FXML
    public void onCoop(ActionEvent event) throws IOException {
    	dungeonScreen.setLevel("Coop.json");
    	dungeonScreen.getController().setGameOverScreen(gameOverScreen);
    	this.dungeonScreen.start();
    }
    
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
    	this.dungeonScreen = dungeonScreen;
    }
    
    public void setGameOverScreen(GameOverScreen gameOverScreen) {
    	this.gameOverScreen = gameOverScreen;
    }
}
