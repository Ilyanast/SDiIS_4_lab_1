package com.graph.editor.controller;

import com.graph.editor.model.MainModel;
import com.graph.editor.model.SelectedElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.ToolType;
import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.input.MouseEvent;

import static com.graph.editor.model.Parameters.*;

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
            double posX = mouseEvent.getX();
            double posY = mouseEvent.getY();
            double circleCornerX = posX  - CIRCLE_RADIUS - circleCenterOffsetX;
            double circleCornerY = posY  - CIRCLE_RADIUS - circleCenterOffsetY;
            if(circleCornerX < 0) {
                posX = circleCenterOffsetX + CIRCLE_RADIUS + CIRCLE_STROKE_WIDTH / 2.;
            }
            if(circleCornerX > PANE_SIZE_X - (CIRCLE_RADIUS * 2)) {
                posX = PANE_SIZE_X - CIRCLE_RADIUS + circleCenterOffsetX - CIRCLE_STROKE_WIDTH / 2.;
            }
            if(circleCornerY < 0) {
                posY = circleCenterOffsetY + CIRCLE_RADIUS + CIRCLE_STROKE_WIDTH / 2.;
            }
            if(circleCornerY > PANE_SIZE_Y - (CIRCLE_RADIUS * 2)) {
                posY = PANE_SIZE_Y - CIRCLE_RADIUS + circleCenterOffsetY - CIRCLE_STROKE_WIDTH / 2.;
            }
            vertex.setVertexPosition(posX - circleCenterOffsetX,posY- circleCenterOffsetY);
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

