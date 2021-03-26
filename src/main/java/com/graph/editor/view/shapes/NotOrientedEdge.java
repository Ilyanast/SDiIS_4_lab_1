package com.graph.editor.view.shapes;

import javafx.scene.paint.Color;

public class NotOrientedEdge extends Edge {

    public NotOrientedEdge(Vertex sourceVertex) {
        super(sourceVertex);
    }

    public void setTargetPosition(double endX, double endY) {
        updateLinePosition(line, sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY(), endX, endY);
    }

    public void updateEdgePosition() {
        if(targetVertex != null) {
            updateLabelPosition();
            updateLinePosition(line, sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY(),
                    targetVertex.getCircleCenterX(), targetVertex.getCircleCenterY());
        }
    }

    public void makeActive() {
        line.setStroke(Color.GREEN);
    }

    public void makeInactive() {
        line.setStroke(Color.BLACK);
    }

}
