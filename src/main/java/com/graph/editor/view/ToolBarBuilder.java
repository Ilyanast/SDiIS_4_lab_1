package com.graph.editor.view;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ToolBarBuilder {

    private ToolBar toolBar;
    private final int toolBarWidth;

    public ToolBarBuilder(int toolBarWidth) {
        this.toolBarWidth = toolBarWidth;
        createToolBar();
    }

    private void setImageViewSize(ImageView imageView) {
        imageView.setFitHeight(toolBarWidth);
        imageView.setFitWidth(toolBarWidth);
    }

    private void addIcon(int menuNumber){
        ImageView imageView = new ImageView();
        Image image = null;

        try {
            image = new Image(new FileInputStream("src/main/resources/icons/menu_" + menuNumber + ".png"));
        } catch (FileNotFoundException e) {
            System.out.println("Icon is not found!");;
        }

        imageView.setImage(image);
        setImageViewSize(imageView);

        toolBar.getItems().addAll(imageView, new Separator());
    }

    private void createToolBar() {
        toolBar = new ToolBar();

        toolBar.setOrientation(Orientation.VERTICAL);

        for(int i = 0; i < 3; i++){
            addIcon(i);
        }
    }

    public ToolBar getToolBar() {
        return toolBar;
    }
}
