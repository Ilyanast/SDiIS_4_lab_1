package com.graph.editor.controller;

import com.graph.editor.model.CurrentTool;
import javafx.scene.image.ImageView;


public class ToolBarController {

    private final ImageView[] toolBarElements;
    private CurrentTool currentTool;


    public ToolBarController(ImageView[] toolBarElements, CurrentTool currentTool) {
        this.toolBarElements = toolBarElements;
        this.currentTool = currentTool;
        vertexToolClickHandler();
        edgeToolClickHandler();
        orientedEdgeToolClickHandler();
    }

    private void vertexToolClickHandler(){
        toolBarElements[0].setOnMouseClicked(mouseEvent -> currentTool.setCurrentTool(Tool.VERTEX_TOOL));
    }

    private void edgeToolClickHandler(){
        toolBarElements[1].setOnMouseClicked(mouseEvent -> currentTool.setCurrentTool(Tool.EDGE_TOOL));
    }

    private void orientedEdgeToolClickHandler(){
        toolBarElements[2].setOnMouseClicked(mouseEvent -> currentTool.setCurrentTool(Tool.ORIENTED_EDGE_TOOL));
    }

}
