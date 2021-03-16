package com.graph.editor.controller;

import javafx.scene.image.ImageView;


public class ToolBarController {

    private final ImageView[] toolBarElements;
    private Tool currentTool;


    public ToolBarController(ImageView[] toolBarElements, Tool currentTool) {
        this.toolBarElements = toolBarElements;
        this.currentTool = currentTool;
        vertexToolClickHandler();
        edgeToolClickHandler();
        orientedEdgeToolClickHandler();
    }

    private void vertexToolClickHandler(){
        toolBarElements[0].setOnMouseClicked(mouseEvent -> currentTool = Tool.VERTEX_TOOL);
    }

    private void edgeToolClickHandler(){
        toolBarElements[1].setOnMouseClicked(mouseEvent -> currentTool = Tool.EDGE_TOOL);
    }

    private void orientedEdgeToolClickHandler(){
        toolBarElements[2].setOnMouseClicked(mouseEvent -> currentTool = Tool.ORIENTED_EDGE_TOOL);
    }

}
