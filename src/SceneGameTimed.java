


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;





public class SceneGameTimed
{
    private Label myLabelGameTimed_1 = new Label("Game - Timed");
    private Scene gameTimedScene;
    private Stage localStage;
    
    public SceneGameTimed(Stage stage)
    {
        gameTimedScene = makeSceneGameTimed();
        localStage = stage;
    }
    
    public Scene getScene()
    {
        return gameTimedScene;
    }
        
    
    
    public Scene makeSceneGameTimed()
    {
        // Implement the layout of this scene/screen
        Button buttonToSelectGameMenu = new Button("Back - Game Select Menu");
        
        
        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        //set an action on the button using method reference
        buttonToSelectGameMenu.setOnAction(this::buttonClickToGameSelect);
        
        // Add the button and label into the pane
        pane.add(myLabelGameTimed_1, 0, 0);
        pane.add(buttonToSelectGameMenu, 1, 0);
             
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 500,400);
        return scene;
    }
        
    private void buttonClickToGameSelect(ActionEvent event)
    {
       localStage.setTitle("Game Select Menu");
       localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_SELECTGAME));
       localStage.show();

    }
}
