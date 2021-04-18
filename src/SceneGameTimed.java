


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.lwawt.macosx.CSystemTray;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;


public class SceneGameTimed
{
    private Label myLabelGameTimed_1       = new Label("Game - Timed");
    private Label labelRunningTally             = new Label("01234567891234");
    private Scene gameTimedScene;
    private Stage localStage;

    static Button nameChoices[];
    static ArrayList<Candidate> targetDecoys;   // list of one target, N decoy Candidates
    static Candidate currentTarget = null;      // Which on the screen is the target Candidate right now?
    static ArrayList<Candidate> canList;    // The entire list of Candidates
    ImageView imagePerson;                  // Reference to the image shown on the screen
    boolean firstTime = true;
    boolean firstTimePerson = true;
    static int currentSet = 0;                  // which set of the game are we on?
    static int numCorrect = 0;                    // overall counts
    static int numWrong = 0;                      // overall counts
    static int time = 0;                        //time it takes to answer twenty correct questions

    private static Label timeLabel = new Label("Time: 7");

    static GridPane pane;

    public static boolean cancel;
    
    public SceneGameTimed(Stage stage)
    {
        gameTimedScene = makeSceneGameTimed();
        localStage = stage;
        timeLabel = new Label("Time: 0");
        cancel = false;
    }
    
    public Scene getScene()
    {
        return gameTimedScene;
    }
    
    public Scene makeSceneGameTimed()
    {
        // Implement the layout of this scene/screen
        Button buttonToSelectGameMenu = new Button("Back - Game Select Menu");
        nameChoices = new Button[] {
                new Button("1"),
                new Button("2"),
                new Button("3"),
                new Button("4")
        };

        // Create a new grid pane
        pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);
        ///
        // Vertically-arranged buttons to choose names
        VBox vChoices = new VBox();
        vChoices.setPadding(new Insets(10));
        vChoices.setSpacing(20);

        nameChoices[0].setOnAction(this::buttonClickNameChoice_1);
        nameChoices[1].setOnAction(this::buttonClickNameChoice_2);
        nameChoices[2].setOnAction(this::buttonClickNameChoice_3);
        nameChoices[3].setOnAction(this::buttonClickNameChoice_4);

        // Set up the text for the four buttons
        // populateButtons(options, targetDecoys);

        for (int i=0; i<4; i++) {
            VBox.setMargin(nameChoices[i], new Insets(0, 0, 0, 8));
            vChoices.getChildren().add(nameChoices[i]);
        }

        pane.add(vChoices, 2, 1);
        //Setting size for the pane
        pane.setMinSize(500, 300);

        //Setting the padding
        pane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        pane.setVgap(5);
        pane.setHgap(5);


        //set an action on the button using method reference
        buttonToSelectGameMenu.setOnAction(this::buttonClickToGameSelect);

        // Add the button and label into the pane
        pane.add(myLabelGameTimed_1, 0, 0);
        pane.add(labelRunningTally, 0, 4);
        pane.add(timeLabel, 2, 0);

        pane.add(buttonToSelectGameMenu, 1, 0);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 500,400);

        return scene;
    }
        
    private void buttonClickToGameSelect(ActionEvent event)
    {
       localStage.setTitle("Game Select Menu");
       cancel = true;
       localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_SELECTGAME));
       localStage.show();

    }
    private void buttonClickNameChoice_1 (ActionEvent event)
    {
        System.out.println("Choice 1");
        if (currentTarget == targetDecoys.get(0)) {
            System.out.println("CORRECT!");
            numCorrect++;
        } else {
            System.out.println("Nope");
            numWrong++;
        }
        nextSet(canList);
    }
    private void buttonClickNameChoice_2 (ActionEvent event)
    {
        System.out.println("Choice 2");
        if (currentTarget == targetDecoys.get(1)) {
            System.out.println("CORRECT!");
            numCorrect++;
        } else {
            System.out.println("Nope");
            numWrong++;
        }
        nextSet(canList);
    }

    private void buttonClickNameChoice_3 (ActionEvent event)
    {
        System.out.println("Choice 3");

        if (currentTarget == targetDecoys.get(2)) {
            System.out.println("CORRECT!");
            numCorrect++;
        } else {
            System.out.println("Nope");
            numWrong++;
        }
        nextSet(canList);
    }

    private void buttonClickNameChoice_4 (ActionEvent event)
    {
        System.out.println("Choice 4");
        if (currentTarget == targetDecoys.get(3)) {
            System.out.println("CORRECT!");
            numCorrect++;
        } else {
            System.out.println("Nope");
            numWrong++;
        }
        nextSet(canList);
    }

    private void populateButtons(Button[] bs, ArrayList<Candidate> targsDecs)
    {
        Collections.shuffle(targsDecs);

        bs[0].setText(targsDecs.get(0).getFirstName() + " " + targsDecs.get(0).getLastName());
        bs[1].setText(targsDecs.get(1).getFirstName() + " " + targsDecs.get(1).getLastName());
        bs[2].setText(targsDecs.get(2).getFirstName() + " " + targsDecs.get(2).getLastName());
        bs[3].setText(targsDecs.get(3).getFirstName() + " " + targsDecs.get(3).getLastName());
    }

    public void nextSet(ArrayList<Candidate> canList)
    {
        System.out.println("Correct/Wrong: " + numCorrect + " / " + numWrong);
        targetDecoys = TargetMgr.getTargetDecoys(canList);
        currentTarget = targetDecoys.get(0);

        // set up the image of the new target...
        Image image1 = currentTarget.getImage();

        if (firstTimePerson) {
            imagePerson = new ImageView(image1);
            firstTimePerson = false;
        }
        imagePerson.setImage(image1);

        //Setting the position of the image
        imagePerson.setX(50);
        imagePerson.setY(50);

        //setting the fit height and width of the image view
        imagePerson.setFitHeight(250);
        imagePerson.setFitWidth(250);

        //Setting the preserve ratio of the image view
        imagePerson.setPreserveRatio(true);

        // shuffle four names and set up the button text
        populateButtons(nameChoices, targetDecoys);

        if (firstTime) {
            pane.add(imagePerson, 0, 1, 2, 2);
            firstTime = false;
        }

    }

    public void resetTimedGame()
    {
        numCorrect = 0;
        numWrong = 0;
        time = 0;
        nextSet(canList);
    }

    public void initializeTargetCans(ArrayList<Candidate> canList)
    {
        this.canList = canList;
        resetTimedGame();
    }

    public static boolean getCancel(){
        return cancel;
    }

    public static void setCancel(boolean x){
        cancel = x;
    }

    public static void setTimeImg(int t){
        timeLabel.setText("Time: " + t);

        System.out.println("check --- " + t);

    }
}
