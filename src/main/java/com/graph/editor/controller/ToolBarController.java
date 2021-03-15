package com.graph.editor.controller;

import javafx.scene.image.ImageView;


public class ToolBarController {

    ImageView[] toolBarElements;

    public ToolBarController(ImageView[] toolBarElements) {
        this.toolBarElements = toolBarElements;
        vertexToolClickHandler();
        edgeToolClickHandler();
        orientedEdgeToolClickHandler();
    }

    private void vertexToolClickHandler(){
        toolBarElements[0].setOnMouseClicked(mouseEvent -> System.out.println("Tool 1 click"));
    }

    private void edgeToolClickHandler(){
        toolBarElements[1].setOnMouseClicked(mouseEvent -> System.out.println("Tool 2 click"));
    }

    private void orientedEdgeToolClickHandler(){
        toolBarElements[2].setOnMouseClicked(mouseEvent -> System.out.println("Tool 3 click"));
    }

}
