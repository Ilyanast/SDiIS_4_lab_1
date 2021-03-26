package com.graph.editor.view.shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import static com.graph.editor.model.Parameters.ARROW_HEAD_SIZE;

public class OrientedEdge extends Edge {

    private Line firstArrowSide;
    private Line secondArrowSide;


    public OrientedEdge(Vertex sourceVertex) {
        super(sourceVertex);

        createArrowSides();
        setLineParams(firstArrowSide);
        setLineParams(secondArrowSide);

        addArrowHeadToGroup(firstArrowSide, secondArrowSide);
    }


    public void setTargetPosition(double endX, double endY) {
        updateLinePosition(line, sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY(), endX, endY);
        updateArrowHeadPosition(endX, endY);
    }

    public void updateEdgePosition() {
        if(targetVertex != null) {
            updateLabelPosition();
            updateArrowHeadPosition(targetVertex.getCircleCenterX(), targetVertex.getCircleCenterY());
            updateLinePosition(line, sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY(),
                    targetVertex.getCircleCenterX(), targetVertex.getCircleCenterY());
        }
    }

    public void makeActive() {
        line.setStroke(Color.GREEN);
        firstArrowSide.setStroke(Color.GREEN);
        secondArrowSide.setStroke(Color.GREEN);
    }

    public void makeInactive() {
        line.setStroke(Color.BLACK);
        firstArrowSide.setStroke(Color.BLACK);
        secondArrowSide.setStroke(Color.BLACK);
    }


    private void createArrowSides() {
        firstArrowSide = new Line();
        secondArrowSide = new Line();
    }

    private void addArrowHeadToGroup(Line firstArrowSide, Line secondArrowSide) {
        group.getChildren().addAll(firstArrowSide, secondArrowSide);
    }

    private void updateArrowHeadPosition(double endX, double endY) {
        double angle = Math.atan2((endY - sourceVertex.getCircleCenterY()),
                (endX - sourceVertex.getCircleCenterX())) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * ARROW_HEAD_SIZE + endX;
        double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * ARROW_HEAD_SIZE + endY;

        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * ARROW_HEAD_SIZE + endX;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * ARROW_HEAD_SIZE + endY;

        updateLinePosition(firstArrowSide, sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY(), x1, y1);
        updateLinePosition(secondArrowSide, sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY(), x2, y2);
    }

}
