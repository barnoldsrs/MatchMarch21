import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        //create objects
        Button startButton = new Button("Start");
        Button backButton = new Button("Back");
        Label ready = new Label("READY?");

        startButton.setOnAction(this::buttonClickToStartGame);
        backButton.setOnAction(this::buttonClickBack);

        //Setting Fonts to Objects
        startButton.setFont(SceneMaker.getLabelFont());
        backButton.setFont(SceneMaker.getLabelFont());
        ready.setFont(SceneMaker.getTitleFont());

        //add objects to the pane
        pane.add(startButton, 0, 1);
        pane.add(ready, 0,0);
        pane.add(backButton, 0, 2);

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

        // track time
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {

                    for(int i = 0; i <= numSeconds; i++){
                        SceneGameTimed.setTimeImg(i);
                        Thread.sleep(1000);
                        if(SceneMaker.isDebugging() == true) {
                            System.out.println("--------------" + i);
                        }
                    }


                    SceneResultsTimed.updatePlayerScore();

                    //Thread.sleep(5*1000);           // number of seconds * 1000 to convert for milliseconds -- this number is subject to change
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                // cancels the operation to change screens if the menu button was selected while it was running
                if(SceneGameTimed.getCancel() == false) {
                    localStage.setScene(SceneMgr.getScene(SceneMgr.IDX_RESULTSTIMED));
                }

            }
        });
        new Thread(sleeper).start();
    }
}
