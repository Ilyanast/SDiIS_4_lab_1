package com.graph.editor.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class MainSceneBuilder {

    Stage primaryStage;

    public MainSceneBuilder(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void createScrollPane(Scene scene, Canvas canvas){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(canvas);
    }

    public void buildScene(){
        Group root = new Group();
        Canvas canvas = new Canvas(640,480);

        root.getChildren().add(canvas);

        Scene mainScene = new Scene(root)
    }

}
