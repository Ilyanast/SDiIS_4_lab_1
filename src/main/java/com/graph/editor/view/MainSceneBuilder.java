package com.graph.editor.view;

import com.graph.editor.model.ElementsSizeParams;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainSceneBuilder {

    private Scene activeScene;

    public MainSceneBuilder() {
        ElementsSizeParams elementsSizeParams = new ElementsSizeParams();
        buildScene(elementsSizeParams);
    }

    private void setPaneSize(Pane pane, ElementsSizeParams elementsSizeParams){
        pane.setPrefSize(elementsSizeParams.getCanvasSizeX(), elementsSizeParams.getCanvasSizeY());
    }

    private void createActiveScene(ElementsSizeParams elementsSizeParams, VBox verticalRootElement) {
        activeScene = new Scene(verticalRootElement, elementsSizeParams.getSceneSizeX(), elementsSizeParams.getSceneSizeY());
    }


    public void buildScene(ElementsSizeParams elementsSizeParams) {
        ToolBarBuilder toolBarBuilder = new ToolBarBuilder(elementsSizeParams.getToolBarWidth());
        MenuBarBuilder menuBarBuilder = new MenuBarBuilder();
        HBox horizontalRootElement = new HBox();
        VBox verticalRootElement = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        Pane pane = new Pane();

        scrollPane.setContent(pane);
        setPaneSize(pane, elementsSizeParams);
        createActiveScene(elementsSizeParams, verticalRootElement);

        horizontalRootElement.getChildren().addAll(toolBarBuilder.getToolBar(), scrollPane);
        verticalRootElement.getChildren().addAll(menuBarBuilder.getMenuBar(), horizontalRootElement);
    }

    public Scene getActiveScene() {
        return activeScene;
    }

}
