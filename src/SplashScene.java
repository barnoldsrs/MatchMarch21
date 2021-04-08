import javafx.application.Application;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class SplashScene {
    Image logo = new Image("NameGame.png");
    ImageView logoImg = new ImageView(logo);

    private Scene splashScene;
    private Stage localStage;

    GridPane pane;

    public SplashScene(Stage stage)
    {
        splashScene = makeSceneSplash();
        localStage = stage;
    }

    public Scene getScene()
    {
        return splashScene;
    }

    public Scene makeSceneSplash()
    {
        // Create a new grid pane
        pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        int paneWidth = 500;

        /*
         *  Put some sort of memorable graphic up before program exits.
         */

        // position + size for image
        logoImg.setPreserveRatio(true);
        logoImg.setFitWidth(paneWidth);

        pane.add(logoImg, 0, 0);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, paneWidth,400);
        return scene;

    }


}
