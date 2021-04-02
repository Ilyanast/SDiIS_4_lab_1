package com.graph.editor.controller;

import com.graph.editor.model.LoadGraphFromFile;
import com.graph.editor.model.MainModel;
import com.graph.editor.model.SaveGraphToFile;
import com.graph.editor.view.InformationDialogBuilder;
import com.graph.editor.view.MainSceneElements;
import javafx.scene.control.MenuItem;

public class MenuBarController {

    private final MainModel mainModel;
    private final MenuItem[] menuBarItems;
    private final MainSceneElements mainSceneElements;

    public MenuBarController(MainSceneElements mainSceneElements, MainModel mainModel) {
        this.mainSceneElements = mainSceneElements;
        this.mainModel = mainModel;

        menuBarItems = mainSceneElements.getMenuBarItems();

        graphTaskHandler();
        openFileHandler();
        saveAsHandler();
        aboutHandler();
    }

    private void openFileHandler(){
        menuBarItems[0].setOnAction(actionEvent -> new LoadGraphFromFile(mainModel, mainSceneElements.getPane()));
    }

    private void saveAsHandler(){
        menuBarItems[1].setOnAction(actionEvent -> new SaveGraphToFile(mainModel));
    }

    private void graphTaskHandler(){
        menuBarItems[2].setOnAction(actionEvent -> new GraphTaskController(mainModel.getGraph()));
    }

    private void aboutHandler() {
        menuBarItems[3].setOnAction(actionEvent -> new InformationDialogBuilder().showAndWait());
    }

}
