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

    public ToolBarBuilder() {
        createToolBar();
    }

    //TODO FixIcons

    private void addIcon(int menuNumber){
        ImageView imageView = new ImageView();
        Image image = null;

        try {
            image = new Image(new FileInputStream("src/main/resources/icons/menu_" + menuNumber + ".png"));
        } catch (FileNotFoundException e) {
            System.out.println("Icon is not found!");;
        }

        imageView.setImage(image);
        imageView.setFitHeight(32);
        imageView.setFitWidth(32);

        toolBar.getItems().add(imageView);
        toolBar.getItems().add(new Separator());
    }

    private void createToolBar(){
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
