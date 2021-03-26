package com.graph.editor.controller;

import com.graph.editor.model.Graph;
import com.graph.editor.model.MainModel;
import com.graph.editor.view.InformationDialogBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;

public class MenuBarController {

    private final Pane pane;
    private final MainModel mainModel;
    private final MenuItem[] menuBarItems;

    public MenuBarController(MenuItem[] menuBarItems, Pane pane, MainModel mainModel) {
        this.menuBarItems = menuBarItems;
        this.mainModel = mainModel;
        this.pane = pane;

        openFileItemClickHandler();
        saveAsItemClickHandler();
        graphTaskItemClickHandler();
        aboutItemClickHandler();
    }

    private void openFileItemClickHandler(){
        menuBarItems[0].setOnAction(actionEvent -> new LoadGraphFromFile(mainController, pane));
    }

    private void saveAsItemClickHandler(){
        menuBarItems[1].setOnAction(actionEvent -> new SaveGraphToFile(mainController));
    }

    private void graphTaskItemClickHandler(){
        menuBarItems[2].setOnAction(actionEvent -> System.out.println("Item 3 click"));
    }

    private void aboutItemClickHandler() {
        menuBarItems[3].setOnAction(actionEvent -> new InformationDialogBuilder().showAndWait());
    }

}
