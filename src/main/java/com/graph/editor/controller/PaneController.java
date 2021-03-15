package com.graph.editor.controller;

import com.graph.editor.view.shapes.Vertex;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PaneController {

    Pane pane;

    public PaneController(Pane pane) {
        this.pane = pane;
        clickHandler();
    }

    private void clickHandler() {
        pane.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2) {

                Vertex vertex = new Vertex(mouseEvent.getX(), mouseEvent.getY());
                vertex.setIdentifier("My test node");
                pane.getChildren().addAll(vertex.getGroup());

                System.out.println("Click " + mouseEvent.getX() + " " + mouseEvent.getY());
            }
        });
    }

}
