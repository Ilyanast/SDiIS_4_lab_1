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

    private final MenuItem[] menuBarItems;
    private final Graph graph;
    private final Pane pane;

    public MenuBarController(MenuItem[] menuBarItems, Graph graph, Pane pane) {
        this.menuBarItems = menuBarItems;
        this.graph = graph;
        this.pane = pane;

        openFileItemClickHandler();
        saveAsItemClickHandler();
        graphTaskItemClickHandler();
        aboutItemClickHandler();
    }

    private void openFileItemClickHandler(){
        menuBarItems[0].setOnAction(actionEvent -> new LoadGraphFromFile(graph, pane));
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
