import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Formatter;


public class SceneTopScoreMenu
{
    private Label myLabelTopScore_1         = new Label("Top Score Menu");   
    private Scene topScoreMenuScene;
    private static Stage localStage;
    private static Label[] leaderBoard = new Label[TopScoreMgr.TOPSCORE_MAX_ENTRIES];

    static String scoreSpeedTitle;
    static String scoreTimedTitle;
    static Label titleLabel;

    private static boolean isSpeedMode = true;

    private String[] top10Speed = {"Flynn 00:10.34","Nathan 00:10.89","Jill 00:15.78","Kate 00:19.25","Harold 00:23.45",
            "Dan 00:36.76","Pam 00:45.12","Bill 00:50.84","Toby 00:59.85","Alex 01:02.36"};
    private String[] top10Timed = {"Jill 00:10.34","Nathan 00:10.89","Kate 00:15.78","Flynn 00:19.25","Toby 00:23.45",
            "Bill 00:36.76","Alex 00:45.12","Dan 00:50.84","Harold 00:59.85","Pam 01:02.36"};
    private static boolean showingSpeed = true;
    
    private int count = 0;
    
    public SceneTopScoreMenu(Stage stage)
    {
        localStage = stage;
        topScoreMenuScene = makeSceneTopScore();
    }
    
    public Scene getScene()
    {
        return topScoreMenuScene;
    }    
    
    public Label[] getLeaderBoard()
    {
        return leaderBoard;
    }
    
    
    private void buttonClickTopScore(ActionEvent event)
    {
       localStage.setTitle("Main Menu");
       localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_MAINMENU));
       localStage.show();        

    }
    
    public Scene makeSceneTopScore()
    {
        /*
        // Implement the layout of this scene/screen
        Button myButton = new Button("Next");

        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        //set an action on the button using method reference
        myButton.setOnAction(this::buttonClickTopScore);

        // Add the button and label into the pane
        pane.add(myLabelTopScore_1, 0, 0);
        pane.add(myButton, 1, 0);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 500,400);
        return scene;
        */
       
        /////////////////////////////
        
        int c = 0;

        Button buttonToMainMenu = new Button("Main Menu");          //Sends User to Menu Scene
        Button buttonToShuffleMode = new Button("Next Leaderboard");  //Displays next LeaderBoard
        Button buttonToClearLeaderboard = new Button("Clear Leaderboard"); // sends user to SceneClearLeaderboard

        scoreSpeedTitle = "Speed Mode Top Scores";
        scoreTimedTitle = "Timed Mode Top Scores";

        titleLabel = new Label(scoreSpeedTitle);

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30, 10,10,10));
        pane.setMinSize(300,300);
        pane.setVgap(10);
        pane.setHgap(10);

        // align the pane in the top center of the screen
        pane.setAlignment(Pos.TOP_CENTER);

        // add button actions
        buttonToMainMenu.setOnAction(this::buttonClickToMainMenu);
        buttonToShuffleMode.setOnAction(this::buttonClickToShuffleMode);
        buttonToClearLeaderboard.setOnAction(this::buttonClickToClearLeaderboard);

        //Setting Object's Font
        buttonToMainMenu.setFont(SceneMaker.getLabelFont());
        buttonToShuffleMode.setFont(SceneMaker.getLabelFont());
        buttonToClearLeaderboard.setFont(SceneMaker.getLabelFont());
        titleLabel.setFont(SceneMaker.getTitleFont());


        for (int i = 0; i < TopScoreMgr.TOPSCORE_MAX_ENTRIES; i++){
            leaderBoard[i] = new Label();

            // set font
            leaderBoard[i].setFont(SceneMaker.getLabelFont()); //Adding Font to score labels
        }

        // TODO: FIXME: Need better way to align max number
        //              of entries with layout arithmetic
        for (int i = 1; i <= 2; i++){
            for (int j = 1; j <= TopScoreMgr.TOPSCORE_MAX_ENTRIES / 2; j++){
                //System.out.println(c + " " + i + " " +j);
                pane.add(leaderBoard[c],i,j);
                c++;
            }
        }

        // add Hboxes
        HBox titleLabelH = new HBox();
        titleLabelH.getChildren().add(titleLabel);

        HBox mainMenuButtonH = new HBox();
        mainMenuButtonH.getChildren().add(buttonToMainMenu);

        HBox nextScoreBoardH = new HBox();
        nextScoreBoardH.getChildren().add(buttonToShuffleMode);

        HBox clearScoreboardH = new HBox();
        clearScoreboardH.getChildren().add(buttonToClearLeaderboard);

        // set alignment of hboxes
        titleLabelH.setAlignment(Pos.CENTER);
        mainMenuButtonH.setAlignment(Pos.CENTER);
        nextScoreBoardH.setAlignment(Pos.CENTER);
        clearScoreboardH.setAlignment(Pos.CENTER);

        // add things to the pane
        pane.add(titleLabelH, 0, 0, 4, 1);
        pane.add(mainMenuButtonH,1,6);
        pane.add(nextScoreBoardH,2,6);
        pane.add(clearScoreboardH, 1, 7, 2, 1);
        populateLabelsSpeed(false); // false == don't show the Top 10 Score Title

        Scene scene = new Scene(pane,500,400);

        return scene;
    }

    private void buttonClickToMainMenu(ActionEvent event)
    {        
       localStage.setTitle("Main Menu");
       localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_MAINMENU));
       localStage.show();
    }
    
    private void buttonClickToShuffleMode(ActionEvent event)
    {
        if(showingSpeed == true){
            populateLabelsTimed(true);
            showingSpeed = false;
        } else {
            populateLabelsSpeed(true);
            showingSpeed = true;
        }
    }

    public void buttonClickToClearLeaderboard(ActionEvent event){
        localStage.setTitle("Clear Leaderboard");
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_CLEARLEADERBOARD));
        localStage.show();
    }

    public static void setShowingSpeed(boolean newState)
    {
        showingSpeed = newState;
    }

    public static void populateLabelsSpeed(boolean doShowTitle){
        isSpeedMode = true;
        if (doShowTitle == true)
            localStage.setTitle("Top 10 Speed Mode Scores");

        // The top score list may not have all ten entries.  Fill what we can,
        // then insert a placeholder for the rest.
        int numElements = TopScoreMgr.getSpeedmodelist().size();
        for(int i = 0; i < numElements; i++) {
            // define variables
            String speedEntryName = TopScoreMgr.getSpeedEntry(i).getName();
            String speedEntryScore = SceneResultsSpeed.scoreToMinSec((int)TopScoreMgr.getSpeedEntry(i).getTime());

            // check to make sure the name will fit
            if(speedEntryName.length()>=10){
                speedEntryName = speedEntryName.substring(0,10);
            }

            // format the string so it looks nice
            String fText = new Formatter().format("%3d. %-12s %-5s",i+1,speedEntryName,speedEntryScore).toString();

            // set the text to the formatted text
            leaderBoard[i].setText(fText);
        }

        for(int i = numElements; i < TopScoreMgr.TOPSCORE_MAX_ENTRIES; i++) {
            String fText = new Formatter().format("%3d. %s", i+1, "<empty>").toString();
            leaderBoard[i].setText(fText);
        }

        titleLabel.setText(scoreSpeedTitle);

    }

    public static void populateLabelsTimed(boolean doShowTitle){
        isSpeedMode = false;
        if (doShowTitle == true)
            localStage.setTitle("Top 10 Timed Mode Scores");

        // The top score list may not have all ten entries.  Fill what we can,
        // then insert a space as a placeholder for the rest.
        int numElements = TopScoreMgr.getTimedmodelist().size();
        for(int i = 0; i < numElements; i++) {
            // define variables
            String timedEntryName = TopScoreMgr.getTimedEntry(i).getName();
            int timedEntryScore = TopScoreMgr.getTimedEntry(i).getCount();

            // check to make sure the name will fit
            if(timedEntryName.length()>=10){
                timedEntryName = timedEntryName.substring(0,10);
            }

            // format the string so it looks nice
            String fText = new Formatter().format("%3d. %-12s: %-4d",i+1,timedEntryName,timedEntryScore).toString();

            // set the text to the formatted text
            leaderBoard[i].setText(fText);
        }

        for(int i = numElements; i < TopScoreMgr.TOPSCORE_MAX_ENTRIES; i++) {
            String fText = new Formatter().format("%3d. %s", i+1, "<empty>").toString();
            leaderBoard[i].setText(fText);
        }

        titleLabel.setText(scoreTimedTitle);

    }

    public static String getWhichScores(){
        if(isSpeedMode){
            return "Top 10 Speed Mode Scores";
        }else{
            return "Top 10 Timed Mode Scores";
        }
    }
  
}
