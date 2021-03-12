package com.graph.editor;

import com.graph.editor.view.MainSceneBuilder;
import com.graph.editor.view.MainView;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView();
        stage.setScene(mainView.getActiveScene());
        stage.setTitle("SDiIS Graph Editor");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}