package com.graph.editor;

import com.graph.editor.controller.MainController;
import com.graph.editor.model.MainModel;
import com.graph.editor.view.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.graph.editor.model.Parameters.PROGRAM_NAME;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView();
        MainModel mainModel = new MainModel();
        MainController mainController = new MainController(mainModel, mainView.getMainSceneElements());
        setStageParams(stage, mainView.getActiveScene());
    }

    private void setStageParams(Stage stage, Scene activeScene){
        stage.setScene(activeScene);
        stage.setTitle(PROGRAM_NAME);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}