package com.graph.editor.view;

import com.graph.editor.model.Parameters;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarBuilder {

    private MenuBar menuBar;
    private final MenuItem[] menuBarItems;

    public MenuBarBuilder() {
        menuBarItems = new MenuItem[Parameters.AMOUNT_OF_MENUBAR_ITEMS];
        createMenuBar();
    }

    private void createMenuBar(){
        MenuItem openFileItem = new MenuItem("Open File");
        MenuItem saveAsItem = new MenuItem("Save As");
        MenuItem graphTaskItem = new MenuItem("Graph Task");
        MenuItem aboutItem = new MenuItem("About");

        Menu fileMenu = new Menu("File");
        Menu taskMenu = new Menu("Task");
        Menu helpMenu = new Menu("Help");

        addMenuBarItems(openFileItem, saveAsItem, graphTaskItem, aboutItem);

        fileMenu.getItems().addAll(openFileItem, saveAsItem);
        taskMenu.getItems().addAll(graphTaskItem);
        helpMenu.getItems().addAll(aboutItem);

        menuBar = new MenuBar(fileMenu, taskMenu, helpMenu);
    }

    private void addMenuBarItems(MenuItem openFileItem, MenuItem saveAsItem, MenuItem graphTaskItem, MenuItem aboutItem) {
        menuBarItems[0] = openFileItem;
        menuBarItems[1] = saveAsItem;
        menuBarItems[2] = graphTaskItem;
        menuBarItems[3] = aboutItem;
    }

    public MenuItem[] getMenuBarItems() {
        return menuBarItems;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
