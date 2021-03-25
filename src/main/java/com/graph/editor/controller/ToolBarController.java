package com.graph.editor.controller;

import com.graph.editor.model.CurrentTool;
import javafx.scene.image.ImageView;


public class ToolBarController {

    //TODO Сделать графическое выделение текущего тула.

    private final ImageView[] toolBarElements;
    private final CurrentTool currentTool;

    public ToolBarController(ImageView[] toolBarElements, CurrentTool currentTool) {
        this.toolBarElements = toolBarElements;
        this.currentTool = currentTool;
        vertexToolClickHandler();
        edgeToolClickHandler();
        orientedEdgeToolClickHandler();
    }

    private void vertexToolClickHandler(){
        toolBarElements[0].setOnMouseClicked(mouseEvent -> currentTool.setCurrentTool(ToolType.HAND_TOOL));
    }

    private void edgeToolClickHandler(){
        toolBarElements[1].setOnMouseClicked(mouseEvent -> currentTool.setCurrentTool(ToolType.EDGE_TOOL));
    }

    private void orientedEdgeToolClickHandler(){
        toolBarElements[2].setOnMouseClicked(mouseEvent -> currentTool.setCurrentTool(ToolType.ORIENTED_EDGE_TOOL));
    }

}
