import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SceneReadyStartSpeed {

    private Scene readyStartScene;
    private Stage localStage;
    static long initialTime;

    public SceneReadyStartSpeed(Stage stage){
        readyStartScene = makeSceneReadyStart();
        localStage = stage;
    }

    public Scene getScene()
    {
        return readyStartScene;
    }

    private Scene makeSceneReadyStart(){
        //create objects
        Button startButton = new Button("Start");
        Button backButton = new Button("Back");
        Label ready = new Label("READY?");

        // new gridpane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        // align the pane in the top center of the screen
        pane.setAlignment(Pos.TOP_CENTER);

        // create hboxes
        HBox startButtonH = new HBox();
        startButtonH.getChildren().add(startButton);

        HBox backButtonH = new HBox();
        backButtonH.getChildren().add(backButton);

        // center hboxes
        startButtonH.setAlignment(Pos.CENTER);
        backButtonH.setAlignment(Pos.CENTER);

        //Set Object Fonts
        startButton.setFont(SceneMaker.getLabelFont());
        backButton.setFont(SceneMaker.getLabelFont());
        ready.setFont(SceneMaker.getTitleFont());

        //add objects to the pane
        pane.add(startButtonH, 0, 1);
        pane.add(ready, 0,0);
        pane.add(backButtonH, 0, 2);

        startButton.setOnAction(this::buttonClickToStartGame);
        backButton.setOnAction(this::buttonClickBack);

        // return pane
        Scene scene = new Scene(pane, 500,400);
        return scene;
    }

    private void buttonClickBack(ActionEvent event) {
        initialTime = System.currentTimeMillis();
        localStage.setTitle("Game Select Menu");
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_SELECTGAME));
        localStage.show();
    }

    private void buttonClickToStartGame(ActionEvent event) {
        initialTime = System.currentTimeMillis();   // beginning of elapsed time
        localStage.setTitle("Speed Mode Game");
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_GAMESPEED));
        localStage.show();
        SceneGameSpeed.resetSpeedGame();
        SceneGameSpeed.setAnimRunning(true);
    }
}
