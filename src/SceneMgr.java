


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





public class SceneMgr
{
    
    // Index into array of Scenes
    public static final int IDX_SPLASH = 0;
    public static final int IDX_MAINMENU = 1;
    public static final int IDX_TOPSCOREMENU = 2;
    public static final int IDX_TOPSCORESPEED = 3;
    public static final int IDX_TOPSCORETIMED = 4;
    public static final int IDX_SELECTGAME = 5;
    public static final int IDX_GAMESPEED = 6;
    public static final int IDX_GAMETIMED = 7;
    public static final int IDX_BBYE = 8;
    public static final int IDX_READYSTARTTIMED = 9;
    public static final int IDX_READYSTARTSPEED = 10;
    public static final int IDX_LASTINDEX = 11;
    
    
    
    
    
    
    private static Scene[] sceneList = new Scene[12];
    
    public static Scene getScene(int index)
    {
        return sceneList[index];
    }
    
    public static void setScene(int index, Scene scene)
    {
        if ((index >= 0) && (index < IDX_LASTINDEX))
            sceneList[index] = scene;
    }
    
 
}
