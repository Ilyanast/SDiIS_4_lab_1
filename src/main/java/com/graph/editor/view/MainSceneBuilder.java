package com.graph.editor.view;

import com.graph.editor.model.Parameters;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainSceneBuilder {

    private ToolBarBuilder toolBarBuilder;
    private MenuBarBuilder menuBarBuilder;
    private Scene activeScene;
    private Pane pane;

    public MainSceneBuilder() {
        buildScene();
    }

    private void setPaneSize() {
        pane.setPrefSize(Parameters.PANE_SIZE_X, Parameters.PANE_SIZE_Y);
    }

    private void createActiveScene(VBox verticalRootElement) {
        activeScene = new Scene(verticalRootElement, Parameters.SCENE_SIZE_X, Parameters.SCENE_SIZE_Y);
    }

    private void setScrollPaneParams(ScrollPane scrollPane){
        scrollPane.setStyle("-fx-background-color: #b5b5b5; -fx-background-insets: 0, 2;");
        scrollPane.setContent(pane);
    }


    public void buildScene() {
        toolBarBuilder = new ToolBarBuilder();
        menuBarBuilder = new MenuBarBuilder();
        HBox horizontalRootElement = new HBox();
        VBox verticalRootElement = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        pane = new Pane();

        createActiveScene(verticalRootElement);
        setScrollPaneParams(scrollPane);
        setPaneSize();

        verticalRootElement.getChildren().addAll(menuBarBuilder.getMenuBar(), horizontalRootElement);
        horizontalRootElement.getChildren().addAll(toolBarBuilder.getToolBar(), scrollPane);
    }

    public Scene getActiveScene() {
        return activeScene;
    }

    public Pane getPane() {
        return pane;
    }

    public ImageView[] getToolBarItems() {
        return toolBarBuilder.getToolBarItems();
    }

    public MenuItem[] getMenuBarItems() { return menuBarBuilder.getMenuBarItems(); }
}
