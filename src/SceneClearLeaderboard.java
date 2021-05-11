import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SceneClearLeaderboard {

    private Scene clearLeaderboardScene;
    private Stage localStage;

    public SceneClearLeaderboard(Stage stage){
        clearLeaderboardScene = makeClearLeaderboardScene();
        localStage = stage;
    }

    public Scene makeClearLeaderboardScene(){
        // create buttons + text
        Button buttonToClearTimed = new Button("Clear Timed Mode Leaderboard");
        Button buttonToClearSpeed = new Button("CLear Speed Mode Leaderboard");
        Button buttonBackToLeaderboards = new Button("Back");

        Label warningLabel = new Label("WARNING:");
        Label warningTextLabel = new Label("The action of clearing the scores from your scoreboard cannot be undone.");

        // set fonts
        warningLabel.setFont(SceneMaker.getTitleFont());
        warningTextLabel.setFont(SceneMaker.getLabelFont());
        buttonToClearSpeed.setFont(SceneMaker.getLabelFont());
        buttonToClearTimed.setFont(SceneMaker.getLabelFont());
        buttonBackToLeaderboards.setFont(SceneMaker.getLabelFont());

        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(20);

        // align the pane in the top center of the screen
        pane.setAlignment(Pos.TOP_CENTER);

        // create HBoxes
        HBox warningLabelH = new HBox();
        warningLabelH.getChildren().add(warningLabel);

        HBox warningTextH = new HBox();
        warningTextH.getChildren().add(warningTextLabel);

        HBox clearSpeedH = new HBox();
        clearSpeedH.getChildren().add(buttonToClearSpeed);

        HBox clearTimeH = new HBox();
        clearTimeH.getChildren().add(buttonToClearTimed);

        HBox backButtonH = new HBox();
        backButtonH.getChildren().add(buttonBackToLeaderboards);

        // center objects in HBoxes
        warningLabelH.setAlignment(Pos.CENTER);
        warningTextH.setAlignment(Pos.CENTER);
        clearSpeedH.setAlignment(Pos.CENTER);
        clearTimeH.setAlignment(Pos.CENTER);
        backButtonH.setAlignment(Pos.CENTER);

        // add actions to buttons
        buttonBackToLeaderboards.setOnAction(this::backButton);
        buttonToClearSpeed.setOnAction(this::clearSpeed);
        buttonToClearTimed.setOnAction(this::clearTimed);

        // add objects to the pane
        pane.add(warningLabelH, 0, 0);
        pane.add(warningTextH, 0, 1);
        pane.add(clearSpeedH, 0, 2);
        pane.add(clearTimeH, 0, 3);
        pane.add(backButtonH, 0, 4);

        Scene scene = new Scene(pane, 500,400);
        return scene;
    }

    private void clearTimed(ActionEvent event) {

    }

    private void clearSpeed(ActionEvent event) {

    }

    private void backButton(ActionEvent event) {
        localStage.setTitle(SceneTopScoreMenu.getWhichScores());
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_TOPSCOREMENU));
        localStage.show();
    }

    public Scene getScene() {
        return clearLeaderboardScene;
    }
}
