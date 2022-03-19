package com.example.midtermpart2;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
/** Represents JavaFX Scene for Animation view
 */
public class AnimationScene {
    private Stage appStage;
    private Scene homeScene;
    public Button homeButton;
    public VBox animationLayout;
    public Scene animationScene;

    public AnimationScene (){}
    /** Initializes AboutScene with
     * @param stage representing JavaFX app home stage
     * @param home representing JavaFX app home scene
     */
    public AnimationScene (Stage stage, Scene home){
        appStage = stage;
        homeScene = home;
    }
    /** Adds all controls, and layouts for Animation Scene
     */
    public void setScene () {
        // Initialize all layouts, scenes
        animationLayout = new VBox();
        animationLayout.setAlignment(Pos.CENTER);
         animationScene = new Scene(animationLayout,300,300);

        // Create all buttons and set handler
        homeButton = new Button("Back to Main");
            homeButton.setMinWidth(200);
            homeButton.setOnAction(e -> {
            animationLayout.getChildren().clear();
            appStage.setScene(homeScene);
        });
    }
    /** Adds all animation graphics and animations for Animation Scene
     */
    public void startAnimation () {
        // animate stuff
        // todo: MOVE ANY SHAPE FROM LEFT TO RIGHT OVER 2 SECONDS, WHEN IT REAC
        double radius = 20.0;
        Group root = new Group();
        Canvas canvas = new Canvas();
        canvas.widthProperty().bind(appStage.widthProperty());
        canvas.heightProperty().setValue(200);
        root.getChildren().add(canvas);

        animationLayout.getChildren().addAll(homeButton, root);
        appStage.setScene(animationScene);

        // do animation stuff after scene
        Circle circle = new Circle(0, 0, radius);
        circle.setFill(Color.PLUM);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        appStage.heightProperty().doubleValue();
        // Setup animation path and path transition
        // generate horizontal path
        Line line = new Line();
        line.setStartX(0 + radius);
        line.setStartY(appStage.heightProperty().doubleValue() / 2);
        line.setEndX(appStage.widthProperty().doubleValue() - radius);
        line.setEndY(appStage.heightProperty().doubleValue() / 2);

        // generat pathtransition
        final PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(2.0));
        pathTransition.setPath(line);
        pathTransition.setNode(circle);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);

        // animate
        root.getChildren().add(circle);

        // timeline animation event
        EventHandler eventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pathTransition.play();
            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.millis(20), eventHandler);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        System.out.println("Clicked on Animation button");
    }
}
