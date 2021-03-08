
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



public class SceneBbye
{
    
    private Label myLabelBbye_1       = new Label("B'bye");
    private Scene bByeScene;
    private Stage localStage;
    
    public SceneBbye(Stage stage)
    {
        bByeScene = makeSceneBbye();
        localStage = stage;
    }
    
    public Scene getScene()
    {
        return bByeScene;
    }
    
    public Scene makeSceneBbye()
    {               
        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        /*
         *  Put some sort of memorable graphic up before program exits.
         */
        
        // Add the button and label into the pane
        pane.add(myLabelBbye_1, 0, 0);
        
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 500,400);
        return scene;

    }
     
}
