package com.graph.editor.view;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ToolBarBuilder {

    private ToolBar toolBar;

    public ToolBarBuilder() {
        createToolBar();
    }

    //TODO FixIcons

    private void addIcon(int menuNumber){
        ImageView imageView = new ImageView();
        Image image = new Image("file://src/main/resources/icons/menu_0.png");

        imageView.setImage(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        toolBar.getItems().add(imageView);
        toolBar.getItems().add(new Separator());
    }

    private void createToolBar(){
        toolBar = new ToolBar();

        toolBar.setOrientation(Orientation.VERTICAL);
        addIcon(0);
    }

    public ToolBar getToolBar() {
        return toolBar;
    }
}
