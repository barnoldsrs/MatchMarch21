import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SceneResultsTimed {

    private Scene readyStartScene;
    private Stage localStage;

    String username;
    TextField nameInput;

    private static Label playerScore;

    public SceneResultsTimed(Stage stage){
        readyStartScene = makeSceneResultsTimed();
        localStage = stage;
    }

    public Scene getScene()
    {
        return readyStartScene;
    }

    private Scene makeSceneResultsTimed(){
        // new gridpane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        // align the pane in the top center of the screen
        pane.setAlignment(Pos.TOP_CENTER);

        //create objects
        nameInput = new TextField("Name");
        Button submitScoreButton = new Button("Submit Score");
        Button buttonToMainMenu = new Button("Main Menu");
        playerScore = new Label("Score Placeholder");

        //Setting Object's Fonts
        nameInput.setFont(SceneMaker.getLabelFont());
        submitScoreButton.setFont(SceneMaker.getLabelFont());
        buttonToMainMenu.setFont(SceneMaker.getLabelFont());
        playerScore.setFont(SceneMaker.getTitleFont());

        // create Hboxes
        HBox nameInputH = new HBox();
        nameInputH.getChildren().add(nameInput);

        HBox menuButtonH = new HBox();
        menuButtonH.getChildren().add(buttonToMainMenu);

        HBox submitScoreH = new HBox();
        submitScoreH.getChildren().add(submitScoreButton);

        HBox score = new HBox();
        score.getChildren().add(playerScore);

        // center Hboxes
        nameInputH.setAlignment(Pos.CENTER);
        menuButtonH.setAlignment(Pos.CENTER);
        submitScoreH.setAlignment(Pos.CENTER);
        score.setAlignment(Pos.CENTER);

        // add button actions
        submitScoreButton.setOnAction(this::buttonClickToSubmitScore);
        buttonToMainMenu.setOnAction(this::buttonClickToMainMenu);

        //add objects to the pane
        pane.add(nameInputH, 0, 1);
        pane.add(score, 0,0);
        pane.add(submitScoreH, 0, 2);
        pane.add(menuButtonH,0,3);

        // return pane
        Scene scene = new Scene(pane, 500,400);

        // collects data from user

        return scene;
    }

    private void buttonClickToMainMenu(ActionEvent event) {
        localStage.setTitle("Main Menu");
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_MAINMENU));
        localStage.show();
    }

    private void buttonClickToSubmitScore(ActionEvent event) {
        TopScoreMgr.submitTimedScore(new TopScoreEntry(nameInput.getText() , SceneGameTimed.numCorrect, SceneGameTimed.time));
        SceneTopScoreMenu.populateLabelsTimed(true);
        localStage.setTitle("Top 10 Timed Mode Scores");
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_TOPSCOREMENU));
        localStage.show();
    }

    public static void updatePlayerScore() {
        playerScore.setText(Integer.toString(SceneGameSpeed.numCorrect));
    }
}
