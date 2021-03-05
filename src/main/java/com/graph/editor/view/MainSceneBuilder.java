package com.graph.editor.view;

import com.graph.editor.model.MainWindowParams;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainSceneBuilder {

    private Scene activeScene;

    public MainSceneBuilder() {
        buildScene();
    }

    private void setSizeParams(Pane pane, VBox verticalRootElement){
        MainWindowParams mainWindowParams = new MainWindowParams();
        setSceneSize(mainWindowParams, verticalRootElement);
        setPaneSize(mainWindowParams, pane);
    }

    private void setSceneSize(MainWindowParams mainWindowParams, VBox verticalRootElement) {
        activeScene = new Scene(verticalRootElement, mainWindowParams.getSceneSizeX(), mainWindowParams.getSceneSizeY());
    }

    private void setPaneSize(MainWindowParams mainWindowParams, Pane pane){
        pane.setPrefSize(mainWindowParams.getCanvasSizeX(), mainWindowParams.getCanvasSizeY());
    }


    private void buildScene() {
        MenuBarBuilder menuBarBuilder = new MenuBarBuilder();
        ToolBarBuilder toolBarBuilder = new ToolBarBuilder();
        ScrollPane scrollPane = new ScrollPane();
        HBox horizontalRootElement = new HBox();
        VBox verticalRootElement = new VBox();
        Pane pane = new Pane();

        setSizeParams(pane, verticalRootElement);
        horizontalRootElement.getChildren().addAll(toolBarBuilder.getToolBar(), scrollPane);
        verticalRootElement.getChildren().addAll(menuBarBuilder.getMenuBar(), horizontalRootElement);
        scrollPane.setContent(pane);
    }

    public Scene getActiveScene() {
        return activeScene;
    }

}
