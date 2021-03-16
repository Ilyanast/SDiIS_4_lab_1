package com.graph.editor.controller;

import com.graph.editor.view.shapes.Vertex;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PaneController {

    Pane pane;
    Tool currentTool;

    public PaneController(Pane pane, Tool currentTool) {
        this.currentTool = currentTool;
        this.pane = pane;
        clickHandler();
    }

    private void clickHandler() {
        pane.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2 && mouseEvent.getButton() == MouseButton.PRIMARY) {

                Vertex vertex = new Vertex(mouseEvent.getX(), mouseEvent.getY());
                vertex.setIdentifier("My test node");
                pane.getChildren().addAll(vertex.getGroup());

                System.out.println("Click " + mouseEvent.getX() + " " + mouseEvent.getY());
            }
        });
    }

}
