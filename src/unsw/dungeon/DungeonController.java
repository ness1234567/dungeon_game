package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController{

    @FXML
    private GridPane squares;

    private List<ImageView> entityImages;
    private Goal goal;
    private int currentRow;
    
    private Player playerA;
    private Player playerB;

    private Dungeon dungeon;
    
    private GameOverScreen gameOverScreen;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, Goal goal) {
        this.dungeon = dungeon;
        this.goal = goal;
        this.playerA = dungeon.getPlayerA();
        this.playerB = dungeon.getPlayerB();
        this.entityImages = new ArrayList<>(initialEntities);
        this.currentRow = 0;
        trackDungeonComplete();
        trackPlayerDeath();
    }
    
    public GridPane getSquares() {
    	return this.squares;
    }
    
    public void startDungeon() {
    	dungeon.setPaused(false);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : entityImages)
            squares.getChildren().add(entity);
        for (int i = 0; i <dungeon.getHeight(); i++) {//add extra room on the bottom
        	for(int j = 0; j<10; j++) {
	        	squares.add(new ImageView(ground), dungeon.getWidth()+j,i);
        	}
        }
        
        Text goalText = new Text("Goals");
        goalText.setUnderline(true);
    	squares.add(goalText, dungeon.getWidth(), currentRow,2,1);
    	currentRow ++;

        createGoals(goal, 0);
        
        createPlayerUI(0);
        trackPlayerKey(0);
        if(this.playerB != null) {
        	trackPlayerKey(1);
            createPlayerUI(1);
        }
        
    }
    
    void createGoals(Goal g,int indent) {


    	if (g instanceof andGoal) {
    		andGoal newG = (andGoal)g;
    		CheckBox checkbox = new CheckBox();
    		checkbox.selectedProperty().bind(newG.getProperty());
    		squares.add(checkbox, dungeon.getWidth()+ indent, currentRow,5,1);
	    	squares.add(new Text(g.toString()), dungeon.getWidth()+ indent + 1, currentRow,5,1);
	    	currentRow++;
	    	for(int i = 0; i<2; i++) {
	    		createGoals(newG.getSubGoals().get(i),indent+1);
	    	}
    	} else if(g instanceof orGoal) {
    		orGoal newG = (orGoal)g;
    		CheckBox checkbox = new CheckBox();
    		checkbox.selectedProperty().bind(newG.getProperty());
    		squares.add(checkbox, dungeon.getWidth()+ indent, currentRow,5,1);
	    	squares.add(new Text(g.toString()), dungeon.getWidth()+ indent+1, currentRow,5,1);
	    	currentRow++;
	    	for(int i = 0; i<2; i++) {
	    		createGoals(newG.getSubGoals().get(i), indent+1 );
	    	}
    	} else {
    		CheckBox checkbox = new CheckBox();
    		
    		if(g instanceof treasureGoal) {
    			treasureGoal newG = (treasureGoal)g;
        		checkbox.selectedProperty().bind(newG.getProperty());
    		} else if(g instanceof exitGoal) {
    			exitGoal newG = (exitGoal)g;
        		checkbox.selectedProperty().bind(newG.getProperty());
    		} else if(g instanceof switchGoal) {
    			switchGoal newG = (switchGoal)g;
        		checkbox.selectedProperty().bind(newG.getProperty());
    		} else if(g instanceof enemyGoal) {
    			enemyGoal newG= (enemyGoal)g;
        		checkbox.selectedProperty().bind(newG.getProperty());
    		}
    		squares.add(checkbox, dungeon.getWidth()+ indent, currentRow,5,1);
	    	squares.add(new Text(g.toString()), dungeon.getWidth()+ indent+ 1, currentRow,5,1);
	    	currentRow++;
		}
    	
    }
    
    void createPlayerUI(int number) {
    	int offset = 0;
    	Player player = dungeon.getPlayerA();
    	Text playerText = new Text("Player 1");

    	if(number == 1) {
    		offset = dungeon.getWidth()-5;
    		player = dungeon.getPlayerB();
    		playerText.setText("Player 2");
    	}
    	playerText.setUnderline(true);
    	squares.add(playerText, offset, dungeon.getHeight(),3,1);
    	squares.add(new Text("Invincible: "), offset, dungeon.getHeight()+1,3,1);
    	squares.add(new Text("Weapon Uses: "), offset, dungeon.getHeight()+2,3,1);
    	squares.add(new Text("Bombs: "), offset, dungeon.getHeight()+3,2,1);
    	squares.add(new Text("Current Key: "), offset, dungeon.getHeight()+4,3,1);

    	Text status = new Text();
    	status.textProperty().bindBidirectional(player.getStatusProperty(), new BooleanStringConverter());
    	squares.add(status, offset + 3, dungeon.getHeight()+1);
    	
    	Text weaponUses = new Text();
    	weaponUses.textProperty().bindBidirectional(player.getWeaponProperty(), new NumberStringConverter());
    	squares.add(weaponUses, offset + 3, dungeon.getHeight()+2);

    	Text bombs = new Text();
    	bombs.textProperty().bindBidirectional(player.getBombProperty(), new NumberStringConverter());
    	squares.add(bombs, offset+ 2, dungeon.getHeight()+3);
    	
    }
    
    @FXML
    public void handleKeyPress(KeyEvent event) {
    	if(dungeon.getPaused()) {
    		return;
    	}
        switch (event.getCode()) {
        case W:
            playerA.moveUp();
            break;
        case S:
            playerA.moveDown();
            break;
        case A:
            playerA.moveLeft();
            break;
        case D:
            playerA.moveRight();
            break;
        case I:
            playerA.attackUp();
            break;
        case J:
            playerA.attackLeft();
            break;
        case K:
            playerA.attackDown();
            break;
        case L:
            playerA.attackRight();
            break;
        case SPACE:
            playerA.dropBomb();
            break;
        case F:
            playerA.dropKey();
            break;

        default:
            break;
        }
        
        if(dungeon.getPlayerB() != null) {
        	switch (event.getCode()) {
            case UP:
                playerB.moveUp();
                break;
            case DOWN:
                playerB.moveDown();
                break;
            case LEFT:
                playerB.moveLeft();
                break;
            case RIGHT:
                playerB.moveRight();
                break;
            case NUMPAD8:
                playerB.attackUp();
                break;
            case NUMPAD4:
                playerB.attackLeft();
                break;
            case NUMPAD5:
                playerB.attackDown();
                break;
            case NUMPAD6:
                playerB.attackRight();
                break;
            case NUMPAD7:
                playerB.dropBomb();
                break;
            case NUMPAD9:
                playerB.dropKey();
                break;

            default:
                break;
            }
        }
    }
    
    public void setGameOverScreen(GameOverScreen gameOverScreen) {
		System.out.println("Setting gameoverscreen");
    	this.gameOverScreen = gameOverScreen;
    	if(gameOverScreen == null) {
			System.out.println("Fail");
		} else {
			System.out.println("Success!");
		}
    }
    
    public void checkGameOverScreen() {
    	if(gameOverScreen == null) {
			System.out.println("Is NULL");
		} else {
			System.out.println("not NULL!");
		}
    }
    
    private void trackDungeonComplete() {
        dungeon.getCompleteProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if(newValue == true)
					if(gameOverScreen == null) {
						System.out.println("NULL");
					}
					gameOverScreen.setTitle("Dungeon Complete! You Win");
					gameOverScreen.start();
			}
        });
    }
    
    private void trackPlayerDeath() {
        dungeon.getPlayerCountProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	System.out.print("player count = ");
            	System.out.println(dungeon.getPlayerCount());
            	if(dungeon.getPlayerCount() == 0) {
            		if(gameOverScreen == null) {
						System.out.println("NULL");
					}
					gameOverScreen.setTitle("Game Over! You Lose");
            		gameOverScreen.start();
            	}
            }
        });
    }
    
    private void trackPlayerKey(int number) {
    	Player player = dungeon.getPlayerA();
    	int offset = 0;
    	if(number == 1) {
    		player = dungeon.getPlayerB();
    		offset = dungeon.getWidth() - 5;
    	}
    	Text key = new Text("NA");
    	squares.add(key, offset + 3, dungeon.getHeight()+4);
    	
    	
        player.getKeyProperty().addListener(new ChangeListener<Key>() {
            @Override
            public void changed(ObservableValue<? extends Key> observable,
                    Key oldValue, Key newValue) {
            	if(newValue == null) {
            		key.setText("NA");
            	} else {
            		key.setText(Integer.toString(newValue.getID()));
            	}
            }
        });
    }
}

