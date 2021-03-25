package com.graph.editor.controller;

import com.graph.editor.model.Graph;
import com.graph.editor.view.InformationDialogBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;

public class MenuBarController {

    private final Pane pane;
    private final MenuItem[] menuBarItems;
    private final MainController mainController;

    public MenuBarController(MenuItem[] menuBarItems, Pane pane, MainController mainController) {
        this.pane = pane;
        this.menuBarItems = menuBarItems;
        this.mainController = mainController;

        openFileItemClickHandler();
        saveAsItemClickHandler();
        graphTaskItemClickHandler();
        aboutItemClickHandler();
    }

    private void openFileItemClickHandler(){
        menuBarItems[0].setOnAction(actionEvent -> new LoadGraphFromFile(mainController, pane));
    }

    private void saveAsItemClickHandler(){
        menuBarItems[1].setOnAction(actionEvent -> System.out.println("Item 2 click"));
    }

    private void graphTaskItemClickHandler(){
        menuBarItems[2].setOnAction(actionEvent -> System.out.println("Item 3 click"));
    }

    private void aboutItemClickHandler() {
        menuBarItems[3].setOnAction(actionEvent -> new InformationDialogBuilder().showAndWait());
    }

}
