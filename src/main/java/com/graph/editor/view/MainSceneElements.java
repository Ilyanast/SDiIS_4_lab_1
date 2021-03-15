package com.graph.editor.view;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MainSceneElements {

    private ImageView[] toolBarItems;
    private MenuItem[] menuBarItems;
    private Pane pane;

    public ImageView[] getToolBarItems() {
        return toolBarItems;
    }

    public MenuItem[] getMenuBarItems() {
        return menuBarItems;
    }

    public Pane getPane() {
        return pane;
    }

    public void setToolBarItems(ImageView[] toolBarItems) {
        this.toolBarItems = toolBarItems;
    }

    public void setMenuBarItems(MenuItem[] menuBarItems) {
        this.menuBarItems = menuBarItems;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
