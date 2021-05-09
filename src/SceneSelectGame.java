import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;



public class SceneSelectGame
{
    
    private Label myLabelSelectGame_1       = new Label("Select Game");
    private Scene selectGameScene;
    private Stage localStage;
    
    public SceneSelectGame(Stage stage)
    {
        selectGameScene = makeSceneSelectGame();
        localStage = stage;
    }
    
    public Scene getScene()
    {
        return selectGameScene;
    }
        
    
    
    public Scene makeSceneSelectGame()
    {
        // Implement the layout of this scene/screen
        Button buttonToMainMenu = new Button("Back");
        Button buttonToTimedModeGame = new Button("Timed Mode");
        Button buttonToSpeedModeGame  = new Button("Speed Mode");
        
        //Setting Object's Fonts
        myLabelSelectGame_1.setFont(SceneMaker.getTitleFont());
        buttonToMainMenu.setFont(SceneMaker.getLabelFont());
        buttonToSpeedModeGame.setFont(SceneMaker.getLabelFont());
        buttonToTimedModeGame.setFont(SceneMaker.getLabelFont());


        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        // align the pane in the top center of the screen
        pane.setAlignment(Pos.TOP_CENTER);

        // Create Hboxes
        HBox timedModeButtonH = new HBox();
        timedModeButtonH.getChildren().add(buttonToTimedModeGame);

        HBox speedModeButtonH = new HBox();
        speedModeButtonH.getChildren().add(buttonToSpeedModeGame);

        HBox mainMeunButtonH = new HBox();
        mainMeunButtonH.getChildren().add(buttonToMainMenu);

        HBox selectGameTextH = new HBox();
        selectGameTextH.getChildren().add(myLabelSelectGame_1);

        // set alignment for the HBox objects created above
        timedModeButtonH.setAlignment(Pos.CENTER);
        speedModeButtonH.setAlignment(Pos.CENTER);
        mainMeunButtonH.setAlignment(Pos.CENTER);
        selectGameTextH.setAlignment(Pos.CENTER);

        //set an action on the button using method reference
        buttonToMainMenu.setOnAction(this::buttonClickToMainMenu);
        buttonToTimedModeGame.setOnAction(this::buttonClickToTimeModeGame);
        buttonToSpeedModeGame.setOnAction(this::buttonClickToSpeedModeGame);

        
        // Add the button and label into the pane
        pane.add(selectGameTextH, 0, 0);
        pane.add(mainMeunButtonH, 0, 3);
        pane.add(timedModeButtonH, 0, 2);
        pane.add(speedModeButtonH, 0, 1);
        
        
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 500,400);
        return scene;
    }
        
    private void buttonClickToMainMenu(ActionEvent event)
    {
       localStage.setTitle("Main Menu");
       localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_MAINMENU));
       localStage.show();

    }
    
    private void buttonClickToTimeModeGame(ActionEvent event)
    {
       localStage.setTitle("Timed Mode Game");
       SceneGameTimed.resetTimedGame(); // timer, counters, etc.
       localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_READYSTARTTIMED));
       localStage.show();

    }   
    
    private void buttonClickToSpeedModeGame(ActionEvent event)
    {
       localStage.setTitle("Speed Mode Game");
       /*
        * Do all of the stuff to initialize the speed mode game
        */

        SceneGameSpeed.resetSpeedGame(); // timer, counters, etc.
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_READYSTARTSPEED));
        localStage.show();

    }    
    
}
