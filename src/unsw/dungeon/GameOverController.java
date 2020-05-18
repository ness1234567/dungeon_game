package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GameOverController {

    @FXML
    private Button menuButton;

    @FXML
    private TextField textField;
    
    private StartScreen startScreen;

    @FXML
    public void onMenuButton(ActionEvent event) throws IOException {
    	startScreen.start();
    }

    public void setStartScreen(StartScreen startScreen) {
    	this.startScreen = startScreen;
    }
    
    public void setText(String str) {
    	textField.setText(str);
    }
}
