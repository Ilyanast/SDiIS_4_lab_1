package com.graph.editor.view;

import com.graph.editor.model.MainWindowParams;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainSceneBuilder {

    private Scene activeScene;

    public MainSceneBuilder() {
        buildScene();
    }

    private void setSizeParams(Pane pane, ScrollPane scrollPane, VBox rootElement){
        MainWindowParams mainWindowParams = new MainWindowParams();
      //  setScrollPaneSize(mainWindowParams, scrollPane);
        setSceneSize(mainWindowParams, rootElement);
        setPaneSize(mainWindowParams, pane);
    }

    private void setSceneSize(MainWindowParams mainWindowParams, VBox rootElement) {
        activeScene = new Scene(rootElement, mainWindowParams.getSceneSizeX(), mainWindowParams.getSceneSizeY());
    }

    private void setPaneSize(MainWindowParams mainWindowParams, Pane pane){
        pane.setPrefSize(mainWindowParams.getCanvasSizeX(), mainWindowParams.getCanvasSizeY());
    }

    private void setScrollPaneSize(MainWindowParams mainWindowParams, ScrollPane scrollPane){
        scrollPane.setPrefSize(mainWindowParams.getCanvasSizeX(), mainWindowParams.getCanvasSizeY());
    }

    private void buildScene() {
        MenuBarBuilder menuBarBuilder = new MenuBarBuilder();
        ScrollPane scrollPane = new ScrollPane();
        VBox rootElement = new VBox();
        Pane pane = new Pane();

        setSizeParams(pane, scrollPane, rootElement);
        rootElement.getChildren().addAll(menuBarBuilder.getMenuBar(), scrollPane);
        scrollPane.setContent(pane);
    }

    public Scene getActiveScene() {
        return activeScene;
    }

}
