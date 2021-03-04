package com.graph.editor;

import com.graph.editor.view.MainSceneBuilder;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        MainSceneBuilder mainSceneBuilder = new MainSceneBuilder();
        stage.setScene(mainSceneBuilder.getActiveScene());
        stage.setTitle("SDiIS Graph Drawer");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}