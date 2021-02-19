package com.graph.editor;

import com.graph.editor.view.MainSceneBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainSceneBuilder mainSceneBuilder = new MainSceneBuilder(stage);


        Scene scene = new Scene(640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}