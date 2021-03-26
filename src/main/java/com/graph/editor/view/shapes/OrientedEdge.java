package com.graph.editor.view.shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import static com.graph.editor.model.Parameters.*;

public class OrientedEdge extends Edge {

    private Line firstArrowSide;
    private Line secondArrowSide;


    public OrientedEdge(Vertex sourceVertex) {
        super(sourceVertex);

        createArrowSides();
        setLineParams(firstArrowSide, ARROW_SIDE_LINE_WIDTH);
        setLineParams(secondArrowSide, ARROW_SIDE_LINE_WIDTH);

        addArrowHeadToGroup(firstArrowSide, secondArrowSide);
    }


    public void setTargetPosition(double endX, double endY) {
        updateLinePosition(line, sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY(), endX, endY);

        updateArrowHeadPosition(endX, endY);
    }

    public void updateEdgePosition() {
        if(targetVertex != null) {
            updateLabelPosition();
            updateArrowHeadPosition(getVertexStrokeX(targetVertex.getCircleCenterX(), targetVertex.getCircleCenterY()),
                    getVertexStrokeY(targetVertex.getCircleCenterX(), targetVertex.getCircleCenterY()));
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

        double endX_1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * ARROW_HEAD_SIZE + endX;
        double endY_1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * ARROW_HEAD_SIZE + endY;

        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * ARROW_HEAD_SIZE + endX;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * ARROW_HEAD_SIZE + endY;

        updateLinePosition(firstArrowSide, endX, endY, endX_1, endY_1);
        updateLinePosition(secondArrowSide, endX, endY, x2, y2);
    }

    private double getVertexStrokeX(double endX, double endY) {
        double rab = Math.sqrt(Math.pow(endX - sourceVertex.getCircleCenterX(), 2) + Math.pow(endY - sourceVertex.getCircleCenterY(), 2));
        double k = CIRCLE_RADIUS/rab;

        return (endX += (sourceVertex.getCircleCenterX() - endX) * k);

    }

    private double getVertexStrokeY(double endX, double endY) {
        double rab = Math.sqrt(Math.pow(endX - sourceVertex.getCircleCenterX(), 2) + Math.pow(endY - sourceVertex.getCircleCenterY(), 2));
        double k = CIRCLE_RADIUS/rab;

        return (endY += (sourceVertex.getCircleCenterY() - endY) * k);
    }


}
