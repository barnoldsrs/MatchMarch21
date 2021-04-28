/*
 * SceneMaker
 *
 * Testbed to try out new Scene designs
 *
 */



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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;


public class SceneMaker extends Application
{
    //debugging on/off: when off cleans up terminal so less is printed except start up and closing commentary
    private static boolean debugging = false;

    /*
     * Scenes we need for this application
     */
    private Scene sceneMainMenu;
    private Scene sceneSplash;
    private Scene sceneTopScore;
    private Scene sceneSelectGame;
    private Scene sceneSpeedGame;
    private Scene sceneTimedGame;
    private Scene sceneReadyStartTimed;
    private Scene sceneReadyStartSpeed;
    private Scene sceneResultsSpeed;
    private Scene sceneResultsTimed;
    private Scene sceneBbye;
    
    private TopScoreMgr topScoreMgr;

    private Stage localStage;
    
    //StateEvntTransitionEntry[] mainMenuStateTable;
    static ArrayList<Candidate> canList;    // The entire list of Candidates

    //Font Instantiation
    private static String      fontFamily  = "Impact";
    private static double      labelFontSize    = 13;
    private static FontWeight labelFontWeight  = FontWeight.MEDIUM;
    private static double      titleFontSize    = 25;
    private static FontWeight  titleFontWeight  =  FontWeight.BOLD;


    private static Font labelFont = Font.font(fontFamily, labelFontWeight, labelFontSize);
    private static Font titleFont = Font.font(fontFamily, titleFontWeight , titleFontSize);

 
    @Override
    public void start(Stage stage) throws Exception
    {
        localStage = stage;
        doActualStuff(stage);
    }
    
    public void doActualStuff(Stage stage)
    {
        // Cannot get this to work yet, so...
        //String version = this.getClass().getPackage().getImplementationVersion();
        // ...implement brute force method instead.
        String versionInfo = VersionAbout.getVersionInfo();
        String aboutInfo = VersionAbout.getAboutInfo();
        System.out.println("Version: " + versionInfo);
        System.out.println("About: " + aboutInfo);

        /*
         * Build and display Splash screen first, then start setting up
         * everything else.
         */
        SplashScene sceneObjSplashScene = new SplashScene(stage);
        sceneSplash = sceneObjSplashScene.getScene();
        SceneMgr.setScene(SceneMgr.IDX_SPLASH, sceneSplash);

        // Set the title and scene for the first screen to be visible
        stage.setTitle("Name Game");
        stage.setScene(sceneSplash);
        stage.show();


        /*
         * Make sure CandidateSet file exists.  If it doesn't, create it.
         * Open CandidateSet file, load candidate info and images.
         * Select first candidate and decoy images/names.
         */
        CandidateMgr.checkForCandidateFile();
        CandidateMgr.restoreCandidates();
        canList = CandidateMgr.getCanList();

        /*
         * Make sure the TopScore files (speed and timed) exist.
         * If they don't, create them.
         * Open the files and load the contents.
         */
        topScoreMgr = new TopScoreMgr();
        topScoreMgr.initializeTopScoreLists();

       /*
        * Used for testing by inserting dummy values.  Leave commented unless testing
        * startup sequence.
        topScoreMgr.testModifySpeedList();
        topScoreMgr.testModifyTimedList();
        */


        /*
         * Create the rest of the game's Scenes ahead of time
         */

        // Main menu
       SceneMainMenu sceneObjMainMenu = new SceneMainMenu(stage);
       sceneMainMenu = sceneObjMainMenu.getScene();

       
       // Top score menu
       SceneTopScoreMenu sceneObjTopScoreMenu = new SceneTopScoreMenu(stage);
       sceneTopScore = sceneObjTopScoreMenu.getScene();
       
       // Select game
       SceneSelectGame sceneObjSelectGame = new SceneSelectGame(stage);
       sceneSelectGame = sceneObjSelectGame.getScene();
        
        // Game, speed mode
       SceneGameSpeed sceneObjGameSpeed = new SceneGameSpeed(stage);
       sceneSpeedGame = sceneObjGameSpeed.getScene();
       sceneObjGameSpeed.initializeTargetCans(canList);

       // Game, timed mode
       SceneGameTimed sceneObjGameTimed = new SceneGameTimed(stage);
       sceneTimedGame = sceneObjGameTimed.getScene();
       sceneObjGameTimed.initializeTargetCans(canList);

       // ReadyStart before timed gameplay
        SceneReadyStartTimed sceneObjReadyStartTimed = new SceneReadyStartTimed(stage);
        sceneReadyStartTimed = sceneObjReadyStartTimed.getScene();

        // ReadyStart before speed gameplay
        SceneReadyStartSpeed sceneObjReadyStartSpeed = new SceneReadyStartSpeed(stage);
        sceneReadyStartSpeed = sceneObjReadyStartSpeed.getScene();

        // Result Screen from speed gameplay
        SceneResultsSpeed sceneObjResultsSpeed = new SceneResultsSpeed(stage);
        sceneResultsSpeed = sceneObjResultsSpeed.getScene();

        // Result Screen from Timed gameplay
        SceneResultsTimed sceneObjResultsTimed = new SceneResultsTimed(stage);
        sceneResultsTimed = sceneObjResultsTimed.getScene();
       
       // B'bye screen upon exit
       SceneBbye sceneObjBbye = new SceneBbye(stage);
       sceneBbye = sceneObjBbye.getScene();
       

       SceneMgr.setScene(SceneMgr.IDX_MAINMENU,         sceneMainMenu);
       SceneMgr.setScene(SceneMgr.IDX_MAINMENU,         sceneMainMenu);
       SceneMgr.setScene(SceneMgr.IDX_TOPSCOREMENU,     sceneTopScore);
       SceneMgr.setScene(SceneMgr.IDX_SELECTGAME,       sceneSelectGame);
       SceneMgr.setScene(SceneMgr.IDX_GAMESPEED,        sceneSpeedGame);
       SceneMgr.setScene(SceneMgr.IDX_GAMETIMED,        sceneTimedGame);
       SceneMgr.setScene(SceneMgr.IDX_READYSTARTTIMED,  sceneReadyStartTimed);
       SceneMgr.setScene(SceneMgr.IDX_READYSTARTSPEED,  sceneReadyStartSpeed);
       SceneMgr.setScene(SceneMgr.IDX_RESULTSSPEED,     sceneResultsSpeed);
       SceneMgr.setScene(SceneMgr.IDX_RESULTSTIMED,     sceneResultsTimed);
       SceneMgr.setScene(SceneMgr.IDX_BBYE,             sceneBbye);
       


        /*
         * Delay getting to the Main Menu; let the Splash screen sit there for
         * at least 2 seconds.
         */
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {

                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };

        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                localStage.setTitle("Name Game");
                localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_MAINMENU));

            }
        });
        new Thread(sleeper).start();

        /*
         * Ready to Go!
         * Show the Stage (window)
         */
        stage.show();
    }

    public static boolean isDebugging(){
        return debugging;
    }

    public static Font getLabelFont(){
        return labelFont;
    }

    public static Font getTitleFont(){
        return titleFont;
    }

  
}
