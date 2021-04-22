import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.Static;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SceneResultsSpeed {


    private Scene resultsSpeedScene;
    private Stage localStage;

    private static Label playerScore;

    String username;
    TextField nameInput;

    public SceneResultsSpeed(Stage stage){
        resultsSpeedScene = makeSceneResultsSpeed();
        localStage = stage;
    }

    public Scene getScene()
    {
        return resultsSpeedScene;
    }

    private Scene makeSceneResultsSpeed(){
        // new gridpane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        //create objects
        nameInput = new TextField("Name");
        Button submitScoreButton = new Button("Submit Score");
        Button buttonToMainMenu = new Button("Main Menu");
        playerScore = new Label();

        submitScoreButton.setOnAction(this::buttonClickToSubmitScore);
        buttonToMainMenu.setOnAction(this::buttonClickToMainMenu);

        //add objects to the pane
        pane.add(nameInput, 0, 1);
        pane.add(playerScore, 0,0);
        pane.add(submitScoreButton, 0, 2);
        pane.add(buttonToMainMenu,0,3);

        // return pane
        Scene scene = new Scene(pane, 500,400);

        return scene;
    }



    private void buttonClickToMainMenu(ActionEvent event) {
        localStage.setTitle("Main Menu");
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_MAINMENU));
        localStage.show();
    }

    private void buttonClickToSubmitScore(ActionEvent event) {
        System.out.println("FIXME: buttonClickToSubmitScore():setTitle:Top 10 Speed Mode Scores");
        TopScoreMgr.submitSpeedScore(new TopScoreEntry(nameInput.getText() , SceneGameSpeed.numCorrect, SceneGameSpeed.time));
        SceneTopScoreMenu.populateLabelsSpeed(true);
        localStage.setTitle("Top 10 Speed Mode Scores");
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_TOPSCOREMENU));
        localStage.show();
    }

    public static void updatePlayerScore() {
        playerScore.setText(Integer.toString(SceneGameSpeed.time));
    }
}
