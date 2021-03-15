package com.graph.editor;

import com.graph.editor.controller.MainController;
import com.graph.editor.view.MainView;
import javafx.stage.Stage;

import static com.graph.editor.model.Parameters.PROGRAM_NAME;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView();
        MainController mainController = new MainController(mainView.getMainSceneElements());
        stage.setScene(mainView.getActiveScene());
        stage.setTitle(PROGRAM_NAME);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}