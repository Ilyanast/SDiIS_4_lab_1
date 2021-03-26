package com.graph.editor.controller;

import com.graph.editor.model.MainModel;
import com.graph.editor.model.SelectedElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.ToolType;
import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.input.MouseEvent;

import static com.graph.editor.model.Parameters.PANE_SIZE_X;
import static com.graph.editor.model.Parameters.PANE_SIZE_Y;

public class VertexController {

    private final SelectedElement selectedElement;
    private final CurrentTool currentTool;

    private final Vertex vertex;
    private final MainModel mainModel;

   private double circleCenterOffsetX, circleCenterOffsetY;

    public VertexController(MainModel mainModel, Vertex vertex) {
        this.mainModel = mainModel;
        this.vertex = vertex;

        currentTool = mainModel.getCurrentTool();
        selectedElement = mainModel.getSelectedElement();

        vertex.setOnMousePressed(this::handleOnMousePressedEvent);
        vertex.setOnMouseDragged(this::handleOnMouseDraggedEvent);
    }


    private void handleOnMousePressedEvent(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == ToolType.HAND_TOOL) {
            selectedElement.setSelectedElement(vertex);
            setCircleCenterOffset(mouseEvent.getX(), mouseEvent.getY());
        }
    }

    private void handleOnMouseDraggedEvent(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == ToolType.HAND_TOOL) {
            if(mouseEvent.getX() < 0) {
                vertex.setVertexPosition(0,mouseEvent.getY() - circleCenterOffsetY);
            }
            else if(mouseEvent.getX() > (PANE_SIZE_X - 10)) {
                vertex.setVertexPosition(PANE_SIZE_X,mouseEvent.getY() - circleCenterOffsetY);
            }
            else if(mouseEvent.getY() < 0) {
                vertex.setVertexPosition(mouseEvent.getX() - circleCenterOffsetX,0);
            }
            else if(mouseEvent.getY() > PANE_SIZE_Y - 10) {
                vertex.setVertexPosition(mouseEvent.getX() - circleCenterOffsetX, PANE_SIZE_Y);
            }
            else {
                vertex.setVertexPosition(mouseEvent.getX() - circleCenterOffsetX,mouseEvent.getY()- circleCenterOffsetY);
            }
            updateConnectedEdges(vertex);
        }
    }

    private void updateConnectedEdges(Vertex vertex) {
        mainModel.getGraph().getListOfConnectedEdges(vertex)
                .forEach(Edge::updateEdgePosition);
    }

    private void setCircleCenterOffset(double x_pos, double y_pos) {
        circleCenterOffsetX = x_pos - vertex.getCircleCenterX();
        circleCenterOffsetY = y_pos - vertex.getCircleCenterY();
    }

}

