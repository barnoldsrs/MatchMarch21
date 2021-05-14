


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.control.Label;
import javafx.animation.AnimationTimer;

public class SceneGameSpeed {
    private Label myLabelGameSpeed_1 = new Label("Game - Speed");
    private static Label labelRunningTally = new Label("01234567891234");
    private Scene gameSpeedScene;
    private static Stage localStage;

    // Running time for speed mode.
    private static long animStartTime;
    private static boolean animTimerRunning;
    private static AnimationTimer animTimer;
    private static long animEndTime;
    private static int animSeconds;
    private static int numGotWrong = 0;

    private static String text;

    static Button nameChoices[];
    static ArrayList<Candidate> targetDecoys;   // list of one target, N decoy Candidates
    static Candidate currentTarget = null;      // Which on the screen is the target Candidate right now?
    static ArrayList<Candidate> canList;    // The entire list of Candidates
    static ImageView imagePerson;                  // Reference to the image shown on the screen
    static boolean firstTime = true;
    static boolean firstTimePerson = true;
    static int currentSet = 0;                  // which set of the game are we on?
    static int numCorrect = 0;                    // overall counts
    static int numWrong = 0;                      // overall counts
    static int penalty = 5;                     //penalty modifier on score for answering question wrong (in seconds)
    static final int WIN_CONDITION = 10;        // Number of questions to answer correct to end this game

    private static Label timeLabel = new Label("0");
    private static Label strTime = new Label("Time: ");

    static GridPane pane;

    public SceneGameSpeed(Stage stage) {
        gameSpeedScene = makeSceneGameSpeed();
        localStage = stage;
    }

    public Scene getScene() {
        return gameSpeedScene;
    }
    public Scene makeSceneGameSpeed() {
        // Implement the layout of this scene/screen
        Button buttonToSelectGameMenu = new Button("Back - Game Select Menu");
        nameChoices = new Button[]{
                new Button("1"),
                new Button("2"),
                new Button("3"),
                new Button("4")
        };

        //Setting Object's Font
        nameChoices[0].setFont(SceneMaker.getLabelFont());
        nameChoices[1].setFont(SceneMaker.getLabelFont());
        nameChoices[2].setFont(SceneMaker.getLabelFont());
        nameChoices[3].setFont(SceneMaker.getLabelFont());
        buttonToSelectGameMenu.setFont(SceneMaker.getLabelFont());
        myLabelGameSpeed_1.setFont(SceneMaker.getTitleFont());
        labelRunningTally.setFont(SceneMaker.getLabelFont());
        timeLabel.setFont(SceneMaker.getTitleFont());
        strTime.setFont(SceneMaker.getTitleFont());

        HBox strTimeH = new HBox();
        strTimeH.getChildren().add(strTime);
        strTimeH.setAlignment(Pos.CENTER_RIGHT);

        // Create a new grid pane
        pane = new GridPane();
        pane.setPadding(new Insets(30, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

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
        for (int i = 0; i < 4; i++) {
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
        pane.add(myLabelGameSpeed_1, 0, 0);
        pane.add(labelRunningTally, 0, 4);
        pane.add(timeLabel, 3, 0);
        pane.add(strTimeH, 2, 0);

        pane.add(buttonToSelectGameMenu, 2, 4);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 500, 400);
        return scene;
    }


    public boolean isAnimRunning() {
        return animTimerRunning;
    }

    public static void setAnimRunning(boolean newRunning) {
        if(SceneMaker.isDebugging() == true) {
            System.out.println("setAnimRunning()...");
        }
        if (animTimerRunning == newRunning)
            return;
        animTimerRunning = newRunning;
        if (animTimerRunning == true)
        {
            animStartTime = System.currentTimeMillis();
            if (animTimer == null) {
                animTimer = new AnimationTimer() {
                    public void handle(long now) {
                        long elapsedTime = System.currentTimeMillis() - animStartTime;
                        elapsedTime += numWrong*penalty*1000;
                        timeLabel.setText(text);
                        text = String.format("%1d", (int)(elapsedTime / 1000.0));
                    }
                };
            }
            timeLabel.setText("   0.0 seconds");
            animTimer.start();
        } else {
            animTimerRunning = false;
            animTimer.stop();
        }

    }


    private void buttonClickToGameSelect(ActionEvent event)
    {
        setAnimRunning(false);      // Turn off the timer and calculate elapsed
        resetSpeedGame();           // clear the variables for next time
        localStage.setTitle("Game Select Menu");
        localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_SELECTGAME));
        localStage.show();
    }
    private void buttonClickNameChoice_1 (ActionEvent event)
    {
        if(SceneMaker.isDebugging() == true) {
            System.out.println("Choice 1");
        }
        if (currentTarget == targetDecoys.get(0)) {
            if(SceneMaker.isDebugging() == true) {
                System.out.println("CORRECT!");
            }
            numCorrect++;
        } else {
            if(SceneMaker.isDebugging() == true) {
                System.out.println("Nope");
            }
            numWrong++;
        }
        updateRunningTally();
        nextSet(canList);
    }
    private void buttonClickNameChoice_2 (ActionEvent event)
    {
        if(SceneMaker.isDebugging() == true) {
            System.out.println("Choice 2");
        }
        if (currentTarget == targetDecoys.get(1)) {
            if(SceneMaker.isDebugging() == true) {
                System.out.println("CORRECT!");
            }
            numCorrect++;
        } else {
            if(SceneMaker.isDebugging() == true) {
                System.out.println("Nope");
            }
            numWrong++;
        }
        updateRunningTally();
        nextSet(canList);
    }

    private void buttonClickNameChoice_3 (ActionEvent event)
    {
        if(SceneMaker.isDebugging() == true) {
            System.out.println("Choice 3");
        }

        if (currentTarget == targetDecoys.get(2)) {
            if(SceneMaker.isDebugging() == true) {
                System.out.println("CORRECT!");
            }
            numCorrect++;
        } else {
            if(SceneMaker.isDebugging() == true) {
                System.out.println("Nope");
            }
            numWrong++;
        }
        updateRunningTally();
        nextSet(canList);
    }

    private void buttonClickNameChoice_4 (ActionEvent event)
    {
        if(SceneMaker.isDebugging() == true) {
            System.out.println("Choice 4");
        }
        if (currentTarget == targetDecoys.get(3)) {
            if(SceneMaker.isDebugging() == true) {
                System.out.println("CORRECT!");
            }
            numCorrect++;
        } else {
            if(SceneMaker.isDebugging() == true) {
                System.out.println("Nope");
            }
            numWrong++;
        }
        updateRunningTally();
        nextSet(canList);
    }

    /*
     * populateButtons()
     * Shuffle the list of target and decoys, so that the names will be in random order.
     */
    private static void populateButtons(Button[] bs, ArrayList<Candidate> targsDecs)
    {
        Collections.shuffle(targsDecs);

        bs[0].setText(targsDecs.get(0).getFirstName() + " " + targsDecs.get(0).getLastName());
        bs[1].setText(targsDecs.get(1).getFirstName() + " " + targsDecs.get(1).getLastName());
        bs[2].setText(targsDecs.get(2).getFirstName() + " " + targsDecs.get(2).getLastName());
        bs[3].setText(targsDecs.get(3).getFirstName() + " " + targsDecs.get(3).getLastName());
    }


    public static void nextSet(ArrayList<Candidate> canList)
    {
        if(SceneMaker.isDebugging() == true) {
            System.out.println("Correct/Wrong: " + numCorrect + " / " + numWrong);
        }
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

    private static void updateRunningTally()
    {
        if(numCorrect < WIN_CONDITION) {
            String sTally = "Correct: " + numCorrect + "; Wrong: " + numWrong;
            labelRunningTally.setText(sTally);
        } else {
            setAnimRunning(false);          // stop timer
            animEndTime = System.currentTimeMillis();
            animSeconds = (int) ((animEndTime - animStartTime) / 1000.0);
            animSeconds = animSeconds + (numWrong * penalty);
            timeLabel.setText(String.format("Time: %1d seconds", animSeconds));

            if(SceneMaker.isDebugging() == true) {
                System.out.println("---------------" + animSeconds + "----------");
            }

            SceneResultsSpeed.updatePlayerScore();
            localStage.setTitle("Speed Results Screen");
            localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_RESULTSSPEED));
            localStage.show();
        }
    }

    public static void resetSpeedGame()
    {
        numCorrect = 0;
        numWrong = 0;
        animEndTime = 0;
        animSeconds = 0;
        updateRunningTally();
        nextSet(canList);
    }

    public void initializeTargetCans(ArrayList<Candidate> canList)
    {
        this.canList = canList;
        resetSpeedGame();
    }

    //Getter methods
    public static int getAnimSeconds()
    {
        return animSeconds;
    }

    public static int getNumCorrect() { return numCorrect; }

    public int getNumWrong(){ return numWrong; }

}
