package com.example.midtermpart2;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.IOException;

/** Represents JavaFX application.
 * @author Daniel Park
 */
public class MidtermApplication extends Application {
    /** Starts JavaFX application and display home screen
     * @param stage the main stage of the application where
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Setup Home Screen controls
        VBox homeLayout = new VBox();
        homeLayout.setSpacing(10);
        homeLayout.setAlignment(Pos.CENTER);
        Scene homeScene = new Scene(homeLayout,300,300);
        Button animationButton = new Button("Animation");

        // Add navigation to Animation screen
        animationButton.setMinWidth(200);
        animationButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    // call AnimationScene methods
                    AnimationScene animationScene =
                            new AnimationScene(stage, homeScene);
                    animationScene.setScene();
                    animationScene.startAnimation();
                }
            }
        );
        // Add navigation 2D Graphics Screen
        Button twoDGraphicsButton = new Button("2D Graphics");
        twoDGraphicsButton.setMinWidth(200);
        twoDGraphicsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // call TwoDGraphics Scene
                TwoDGraphicsScene twoDGraphicsScene =
                        new TwoDGraphicsScene(stage, homeScene);
                twoDGraphicsScene.setScene();
            }
        });
        // Add navigation About Graphics Screen
        Button aboutButton = new Button("About");
        aboutButton.setMinWidth(200);
        aboutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AboutScene aboutScene = new AboutScene(stage, homeScene);
                aboutScene.setScene();
            }
        });
        homeLayout.getChildren().
                addAll(animationButton, twoDGraphicsButton, aboutButton);
        stage.setTitle("CSCI2020U Midterm - Daniel Park");
        stage.setScene(homeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}