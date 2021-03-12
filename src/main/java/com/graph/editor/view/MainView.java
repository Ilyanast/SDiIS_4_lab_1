package com.graph.editor.view;

import javafx.scene.Scene;

public class MainView {

    MainSceneBuilder mainSceneBuilder;
    MainSceneElements mainSceneElements;

    public MainView() {
        mainSceneBuilder = new MainSceneBuilder();
        mainSceneElements = new MainSceneElements();
    }

    public Scene getActiveScene() {
        return mainSceneBuilder.getActiveScene();
    }
}
