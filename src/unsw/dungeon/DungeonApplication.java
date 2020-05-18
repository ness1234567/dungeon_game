package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	
        StartScreen startScreen = new StartScreen(primaryStage);
        DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
        GameOverScreen gameOverScreen = new GameOverScreen(primaryStage);
        
        startScreen.getController().setDungeonScreen(dungeonScreen);
        startScreen.getController().setGameOverScreen(gameOverScreen);
        dungeonScreen.getController().setGameOverScreen(gameOverScreen);
        gameOverScreen.getController().setStartScreen(startScreen);
        
        startScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
