import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SceneReadyStartTimed {

    private Scene readyStartScene;
    private Stage localStage;

    private int numSeconds;
    public long startTime;

    public SceneReadyStartTimed(Stage stage) {
        readyStartScene = makeSceneReadyStart();
        localStage = stage;

        numSeconds = 5;
    }

    public Scene getScene()
    {
        return readyStartScene;
    }

    private Scene makeSceneReadyStart(){
        // new gridpane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        // align the pane in the top center of the screen
        pane.setAlignment(Pos.TOP_CENTER);

        //create objects
        Button startButton = new Button("Start");
        Button backButton = new Button("Back");
        Label ready = new Label("READY?");

        // create hboxes
        HBox startButtonH = new HBox();
        startButtonH.getChildren().add(startButton);

        HBox backButtonH = new HBox();
        backButtonH.getChildren().add(backButton);

        // center hboxes
        startButtonH.setAlignment(Pos.CENTER);
        backButtonH.setAlignment(Pos.CENTER);

        // check button clicks
        startButton.setOnAction(this::buttonClickToStartGame);
        backButton.setOnAction(this::buttonClickBack);

        //Setting Fonts to Objects
        startButton.setFont(SceneMaker.getLabelFont());
        backButton.setFont(SceneMaker.getLabelFont());
        ready.setFont(SceneMaker.getTitleFont());

        //add objects to the pane
        pane.add(startButtonH, 0, 1);
        pane.add(ready, 0,0);
        pane.add(backButtonH, 0, 2);

        // return pane
        Scene scene = new Scene(pane, 500,400);
        return scene;
    }

    private void buttonClickBack(ActionEvent event) {
        localStage.setTitle("Game Select Menu");
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_SELECTGAME));
        localStage.show();
    }

    private void buttonClickToStartGame(javafx.event.ActionEvent event){
        localStage.setTitle("Timed Mode Game");
        SceneGameTimed.setCancel(false);
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_GAMETIMED));
        localStage.show();
        SceneGameTimed.startTimerLabel();       // Start the KeyFrame Animation (of time)
    }
}
