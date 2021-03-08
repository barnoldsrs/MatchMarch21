import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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


public class SceneMainMenu
{
    private Label myLabelMainMenu_1         = new Label("Main Menu");
    private Scene mainMenuScene;
    private Stage localStage;
    
    public SceneMainMenu(Stage stage)
    {
        mainMenuScene = makeSceneMainMenu();
        localStage = stage;
    }
    
    public Scene getScene()
    {
        return mainMenuScene;
    }
   
    public Scene makeSceneMainMenu()
    {       
        // Implement the layout of this scene/screen
        Button buttonToTopScoreMenu = new Button("Top Score Lists");
        Button buttonToSelectGameMenu = new Button("Game Select Menu");
        Button buttonToBbyeExit = new Button("Quit");
        
        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        //set an action on the button using method reference
        buttonToTopScoreMenu.setOnAction(this::buttonClickToTopScoreMenu);
        buttonToSelectGameMenu.setOnAction(this::buttonClickToSelectGameMenu);
        buttonToBbyeExit.setOnAction(this::buttonClickToQuit);
        
        
        // Add the button and label into the pane
        pane.add(myLabelMainMenu_1, 0, 0);
        pane.add(buttonToTopScoreMenu, 1, 0);
        pane.add(buttonToSelectGameMenu, 1, 1);
        pane.add(buttonToBbyeExit, 1, 2);        
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 500,400);
        return scene;

    }
    
    
    
    private void buttonClickToTopScoreMenu(ActionEvent event)
    {
       localStage.setTitle("Top Score Menu");
       localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_TOPSCOREMENU));
       localStage.show();

    }

    private void buttonClickToSelectGameMenu(ActionEvent event)
    {
       localStage.setTitle("Game Select Menu");
       localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_SELECTGAME));
       localStage.show();

    }

    private void buttonClickToQuit(ActionEvent event)
    {
       localStage.setTitle("B'bye!");
       localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_BBYE));
       localStage.show();
       // wait 2 seconds, then exit
       // https://stackoverflow.com/questions/26454149/make-javafx-wait-and-continue-with-code
       Task<Void> sleeper = new Task<Void>() {
           @Override
           protected Void call() throws Exception {
               try {
                   
                   Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                localStage.close();

            }
        });
        new Thread(sleeper).start();
        
        


    }
    
}
