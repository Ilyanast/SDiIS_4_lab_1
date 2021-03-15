package com.graph.editor.view;

import com.graph.editor.model.Parameters;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ToolBarBuilder {

    private ToolBar toolBar;
    private final ImageView[] toolBarItems;

    public ToolBarBuilder() {
        toolBarItems = new ImageView[Parameters.AMOUNT_OF_TOOLBAR_ITEMS];
        createToolBar();
    }

    private void setImageViewSize(ImageView imageView) {
        imageView.setFitHeight(Parameters.TOOLBAR_WIDTH);
        imageView.setFitWidth(Parameters.TOOLBAR_WIDTH);
    }

    private Image getImageFromFile(int menuNumber) {
        try {
            return new Image(new FileInputStream(Parameters.ICON_LOCATION + menuNumber + Parameters.ICON_EXPANSION));
        } catch (FileNotFoundException e) {
            System.out.println("Icon is not found!");
        }
        return null;
    }

    private void addImageViewToToolBar(int menuNumber){
        ImageView imageView = new ImageView();
        imageView.setImage(getImageFromFile(menuNumber));
        imageView.setPickOnBounds(true);
        setImageViewSize(imageView);

        toolBar.getItems().addAll(imageView, new Separator());
        toolBarItems[menuNumber] = imageView;
    }

    private void createToolBar() {
        toolBar = new ToolBar();
        toolBar.setOrientation(Orientation.VERTICAL);

        for(int i = 0; i < Parameters.AMOUNT_OF_TOOLBAR_ITEMS; i++){
            addImageViewToToolBar(i);
        }
    }

    public ImageView[] getToolBarItems() {
        return toolBarItems;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }
}
