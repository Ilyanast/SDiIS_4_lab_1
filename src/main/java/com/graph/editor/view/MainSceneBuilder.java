package com.graph.editor.view;

import com.graph.editor.model.SceneParamsReader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainSceneBuilder {

    Scene activeScene;
    BorderPane rootElement;
    int sizeX, sizeY;

    public MainSceneBuilder() {
        loadSceneSizeParams();
        buildScene();
    }

    private void loadSceneSizeParams(){
        SceneParamsReader sceneParamsReader = new SceneParamsReader("sceneParams.txt");
        int[] buffer = sceneParamsReader.getSceneSizeParams();
        sizeX = buffer[0];
        sizeY = buffer[1];
    }

    private void buildScene() {
        MenuBarBuilder menuBarBuilder = new MenuBarBuilder();
        rootElement = new BorderPane();
        rootElement.setTop(menuBarBuilder.getMenuBar());
        activeScene = new Scene(rootElement, sizeX, sizeY);
    }

    public Scene getActiveScene() {
        return activeScene;
    }

}
