package com.graph.editor.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarBuilder {

    private MenuBar menuBar;

    public MenuBarBuilder() {
        createMenuBar();
    }

    private void createMenuBar(){
        MenuItem openFileItem = new MenuItem("Open File");
        MenuItem saveAsItem = new MenuItem("Save As");
        MenuItem aboutItem = new MenuItem("About");

        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");

        fileMenu.getItems().addAll(openFileItem, saveAsItem);
        helpMenu.getItems().addAll(aboutItem);

        menuBar = new MenuBar(fileMenu, helpMenu);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
