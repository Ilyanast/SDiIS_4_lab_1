package com.graph.editor.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarController {

    MenuItem[] menuBarItems;

    public MenuBarController(MenuItem[] menuBarItems) {
        this.menuBarItems = menuBarItems;
        openFileItemClickHandler();
        saveAsItemClickHandler();
        graphTaskItemClickHandler();
        aboutItemClickHandler();
    }

    private void openFileItemClickHandler(){
        menuBarItems[0].setOnAction(actionEvent -> System.out.println("Item 1 click"));
    }

    private void saveAsItemClickHandler(){
        menuBarItems[1].setOnAction(actionEvent -> System.out.println("Item 2 click"));
    }

    private void graphTaskItemClickHandler(){
        menuBarItems[2].setOnAction(actionEvent -> System.out.println("Item 3 click"));
    }

    private void aboutItemClickHandler(){
        menuBarItems[3].setOnAction(actionEvent -> System.out.println("Item 4 click"));
    }

}
