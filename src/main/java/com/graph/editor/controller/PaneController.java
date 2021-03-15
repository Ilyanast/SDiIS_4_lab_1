package com.graph.editor.controller;

import javafx.scene.layout.Pane;

public class PaneController {

    Pane pane;

    public PaneController(Pane pane) {
        this.pane = pane;
        clickHandler();
    }

    private void clickHandler() {
        pane.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2) {
                System.out.println("Click " + mouseEvent.getX() + " " + mouseEvent.getY());
            }
        });
    }

}
