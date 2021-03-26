package com.graph.editor.controller;

import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.MainModel;
import com.graph.editor.model.ToolType;
import com.graph.editor.view.shapes.Edge;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class ToolBarController {

    private final ImageView[] toolBarElements;
    private final MainModel mainModel;

    private final CurrentTool currentTool;
    private final DropShadow shadowEffect;
    private final Pane pane;

    public ToolBarController(ImageView[] toolBarElements, Pane pane, MainModel mainModel) {
        this.toolBarElements = toolBarElements;
        this.mainModel = mainModel;
        this.pane = pane;

        currentTool = mainModel.getCurrentTool();
        shadowEffect = new DropShadow(10, Color.DARKRED);
        toolBarElements[0].setEffect(shadowEffect);

        vertexToolHandler();
        notOrientedEdgeToolHandler();
        orientedEdgeToolHandler();
    }

    private void vertexToolHandler(){
        toolBarElements[0].setOnMouseClicked(mouseEvent -> {
            currentTool.setCurrentTool(ToolType.HAND_TOOL);
            removeEdgeFromPane(mainModel.getEdgeTargetVertices().getEdge());
            mainModel.getEdgeTargetVertices().clear();
            clearEffectFromTools();
            toolBarElements[0].setEffect(shadowEffect);
        });
    }

    private void notOrientedEdgeToolHandler(){
        toolBarElements[1].setOnMouseClicked(mouseEvent -> {
            currentTool.setCurrentTool(ToolType.EDGE_TOOL);
            removeEdgeFromPane(mainModel.getEdgeTargetVertices().getEdge());
            mainModel.getEdgeTargetVertices().clear();
            clearEffectFromTools();
            toolBarElements[1].setEffect(shadowEffect);
        });

    }

    private void orientedEdgeToolHandler(){
        toolBarElements[2].setOnMouseClicked(mouseEvent -> {
            currentTool.setCurrentTool(ToolType.ORIENTED_EDGE_TOOL);
            removeEdgeFromPane(mainModel.getEdgeTargetVertices().getEdge());
            mainModel.getEdgeTargetVertices().clear();
            clearEffectFromTools();
            toolBarElements[2].setEffect(shadowEffect);
        });
    }

    private void removeEdgeFromPane(Edge edge) {
        if(edge != null) {
            pane.getChildren().remove(edge.getGroup());
        }
    }

    private void clearEffectFromTools() {
        for (ImageView imageView : toolBarElements) {
            imageView.setEffect(null);
        }
    }

}
