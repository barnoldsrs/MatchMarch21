import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SceneReadyStartTimed {

    private Scene readyStartScene;
    private Stage localStage;

    public SceneReadyStartTimed(Stage stage) {
        readyStartScene = makeSceneReadyStart();
        localStage = stage;
    }

    public Scene getScene()
    {
        return readyStartScene;
    }

    private Scene makeSceneReadyStart(){
        // new gridpane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        //create objects
        Button startButton = new Button("Start");
        Button backButton = new Button("Back");
        Label ready = new Label("READY?");

        startButton.setOnAction(this::buttonClickToStartGame);
        backButton.setOnAction(this::buttonClickBack);

        //add objects to the pane
        pane.add(startButton, 0, 1);
        pane.add(ready, 0,0);
        pane.add(backButton, 0, 2);

        // return pane
        Scene scene = new Scene(pane, 500,400);
        return scene;
    }

    private void buttonClickBack(ActionEvent event) {
        localStage.setTitle("Game Select Menu");
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_SELECTGAME));
        localStage.show();
    }

    private void buttonClickToStartGame(javafx.event.ActionEvent event) {
        localStage.setTitle("Timed Mode Game");
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_GAMETIMED));
        localStage.show();
    }
}
