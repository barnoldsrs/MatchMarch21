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



public class SceneTopScoreMenu
{
    private Label myLabelTopScore_1         = new Label("Top Score Menu");   
    private Scene topScoreMenuScene;
    private static Stage localStage;
    private static Label[] leaderBoard = new Label[TopScoreMgr.TOPSCORE_MAX_ENTRIES];
    
    
    private String[] top10Speed = {"Flynn 00:10.34","Nathan 00:10.89","Jill 00:15.78","Kate 00:19.25","Harold 00:23.45",
            "Dan 00:36.76","Pam 00:45.12","Bill 00:50.84","Toby 00:59.85","Alex 01:02.36"};
    private String[] top10Timed = {"Jill 00:10.34","Nathan 00:10.89","Kate 00:15.78","Flynn 00:19.25","Toby 00:23.45",
            "Bill 00:36.76","Alex 00:45.12","Dan 00:50.84","Harold 00:59.85","Pam 01:02.36"};
    private boolean showingSpeed = true;
    
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

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10,10,10));
        pane.setMinSize(300,300);
        pane.setVgap(10);
        pane.setHgap(10);

        buttonToMainMenu.setOnAction(this::buttonClickToMainMenu);
        buttonToShuffleMode.setOnAction(this::buttonClickToShuffleMode);


        for (int i = 0; i < TopScoreMgr.TOPSCORE_MAX_ENTRIES; i++){
            leaderBoard[i] = new Label();
        }

        // TODO: FIXME: Need better way to align max number
        //              of entries with layout arithmetic
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < TopScoreMgr.TOPSCORE_MAX_ENTRIES / 2; j++){
                //System.out.println(c + " " + i + " " +j);
                pane.add(leaderBoard[c],i,j);
                c++;
            }
        }



        pane.add(buttonToMainMenu,0,5);
        pane.add(buttonToShuffleMode,1,5);
        populateLabelsSpeed();

        Scene scene = new Scene(pane,400,400);

        return scene;        
      
    }    
       
    private void buttonClickToMainMenu(ActionEvent event)
    {        
       localStage.setTitle("Main Menu");
       localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_MAINMENU));
       localStage.show(); 
       
       //topScoreMgr.submitSpeedScore(new TopScoreEntry("Jerry", 20, 563));        

    }
    
    private void buttonClickToShuffleMode(ActionEvent event)
    {        
    
        if(showingSpeed == true){
            populateLabelsTimed();
            showingSpeed = false;
        } else {
            populateLabelsSpeed();
            showingSpeed = true;
        }
    }    


    public static void populateLabelsSpeed(){
        localStage.setTitle("Top 10 Speed Mode Scores");

        // The top score list may not have all ten entries.  Fill what we can,
        // then insert a placeholder for the rest.
        int numElements = TopScoreMgr.getSpeedmodelist().size();
        for(int i = 0; i < numElements; i++) {
            leaderBoard[i].setText(i + 1 + ". " + TopScoreMgr.getSpeedEntry(i).getName()
                    + " " + TopScoreMgr.getSpeedEntry(i).getTime());
        }

        for(int i = numElements; i < TopScoreMgr.TOPSCORE_MAX_ENTRIES; i++) {
            leaderBoard[i].setText(i + 1 + ". " + "<empty>");
        }

    }

    public void populateLabelsTimed(){
        localStage.setTitle("Top 10 Timed Mode Scores");

        // The top score list may not have all ten entries.  Fill what we can,
        // then insert a space as a placeholder for the rest.
        int numElements = TopScoreMgr.getTimedmodelist().size();
        for(int i = 0; i < numElements; i++) {
            leaderBoard[i].setText(i + 1 + ". " + TopScoreMgr.getTimedEntry(i).getName()
                    + " " + TopScoreMgr.getTimedEntry(i).getTime());
        }

        for(int i = numElements; i < TopScoreMgr.TOPSCORE_MAX_ENTRIES; i++) {
            leaderBoard[i].setText(i + 1 + ". " + "<empty>");
        }

    }
    
/*
    public Scene makeScene1()
    {
        // Implement the layout of this scene/screen
        Button myButton = new Button("LeaderBoard");

        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        //set an action on the button using method reference
        myButton.setOnAction(this::buttonClickScene1);

        // Add the button and label into the pane
        pane.add(myLabelScene1, 1, 0);
        pane.add(myButton, 0, 0);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 300,100);
        return scene;
    }


    private void buttonClickScene1(ActionEvent event)
    {
        // Counts number of button clicks and shows the result on a label
        safeStage.setTitle("Top 10 Speed Mode Scores");
        populateLabelsSpeed();
        safeStage.setScene(scene3);

        // Show the Stage (window)
        safeStage.show();

    }

    ////////////////////////////////////////////////////////////////


    public Scene makeScene2()
    {
        // Implement the layout of this scene/screen
        Button myButton = new Button("Next");

        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        //set an action on the button using method reference
        myButton.setOnAction(this::buttonClickScene2);

        // Add the button and label into the pane
        pane.add(myLabelScene2, 0, 0);
        pane.add(myButton, 1, 0);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 500,400);
        return scene;
    }




    private void buttonClickScene2(ActionEvent event)
    {
        // Counts number of button clicks and shows the result on a label
        count = count + 1;

        myLabelScene2.setText(Integer.toString(count+100));
        {
            safeStage.setTitle("First Scene");
            safeStage.setScene(scene1);

            // Show the Stage (window)
            safeStage.show();
        }


    }

    ////////////////////////////////////////////////////////////////

    
    public Scene makeScene3()
    {
        int c = 0;

        Button menuButton = new Button("Menu");//Sends User to Menu Scene
        Button shuffleButton = new Button("Next");//Displays next LeaderBoard

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10,10,10));
        pane.setMinSize(300,300);
        pane.setVgap(10);
        pane.setHgap(10);

        menuButton.setOnAction(this::menuButtonClickScene3);
        shuffleButton.setOnAction(this::shuffleButtonClickScene3);


        for (int i = 0; i < 10; i++){
            leaderBoard[i] = new Label();
        }

        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 5; j++){
                //System.out.println(c + " " + i + " " +j);
                pane.add(leaderBoard[c],i,j);
                c++;
            }
        }



        pane.add(menuButton,0,5);
        pane.add(shuffleButton,1,5);
        populateLabelsSpeed();

        Scene scene = new Scene(pane,400,400);

        return scene;
    }
    
    private void menuButtonClickScene3(ActionEvent event){
        // BAA
       // FSMBase.handleNewEvent(EvtTransitions.eMainMenu);
       // this.safeStage.setTitle("Menu");
       // this.safeStage.setScene(sceneMainMenu);
        
        
        
        this.safeStage.setTitle("Menu");
        this.safeStage.setScene(scene1);
        topScoreMgr.submitSpeedScore(new TopScoreEntry("Jerry", 20, 563));        
        
        
        
    }
*/


/*
    private void shuffleButtonClickScene3(ActionEvent event){
        if(showingSpeed == true){
            populateLabelsTimed();
            showingSpeed = false;
        } else if (!showingSpeed){
            populateLabelsSpeed();
            showingSpeed = true;
        }
    }


    /////////////////////////////////////////////////////////
*/

    
    
    
  
}
