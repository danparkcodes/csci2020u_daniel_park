package com.example.midtermpart2;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
/** Represents JavaFX Scene for About view
 */
public class TwoDGraphicsScene {
    private Stage appStage;
    private Scene homeScene;
    public Button homeButton;
    public VBox twoDGraphicsLayout;
    public Scene twoDGraphicsScene;

    public TwoDGraphicsScene (){}

    /** Initializes AboutScene with
     * @param stage representing JavaFX app home stage
     * @param home representing JavaFX app home scene
     */
    public TwoDGraphicsScene (Stage stage, Scene home){
        appStage = stage;
        homeScene = home;
    }
    /** Adds all graphics, controls, and layouts for 2D Graphics Scene
     */
    public void setScene () {
        System.out.println("Clicked on Graphics 2D button");

        // Initialize all layouts, scenes
        twoDGraphicsLayout = new VBox();
        twoDGraphicsLayout.setAlignment(Pos.CENTER);
        twoDGraphicsScene = new Scene(twoDGraphicsLayout,300,300);
        homeButton = new Button("Back to Main");
        homeButton.setMinWidth(200);
        homeButton.setOnAction(e -> {
            twoDGraphicsLayout.getChildren().clear();
            appStage.setScene(homeScene);
        });

        Group root = new Group();
        Canvas canvas = new Canvas(300,200);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // drawShapes()
        double startX = 110.0;
        double startY = 60.0;
        double characterHeight = 100.0;
        double characterWidth = 35.0;
        double characterSpace = 10.0;
        gc.setStroke(Color.BLUE);
        //D
        gc.strokeLine
                (startX,startY,startX,startY+characterHeight);
        gc.strokeArc(
                (startX-(characterWidth)), startY,
                characterWidth *2,characterHeight,
                270.0,180.0,
                ArcType.OPEN );
        //P
        startX += characterWidth + characterSpace;

        gc.strokeLine(startX ,startY, startX,startY +characterHeight);
        gc.strokeArc
                (startX-25,startY,
                        characterHeight/2.0,characterHeight/2.0,
                        270.0,180.0,
                        ArcType.OPEN);


        root.getChildren().add(canvas);

        twoDGraphicsLayout.getChildren().addAll(homeButton,root);
        appStage.setScene(twoDGraphicsScene);
    }
}
