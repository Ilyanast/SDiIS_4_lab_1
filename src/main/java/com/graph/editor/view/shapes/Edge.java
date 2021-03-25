package com.graph.editor.view.shapes;

import com.graph.editor.model.Selectable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import static com.graph.editor.model.Parameters.FONT_SIZE;
import static com.graph.editor.model.Parameters.FONT_TYPE;

public class Edge implements Selectable {

    private static final int LINE_WIDTH = 5;

    private final Group group;
    private final Line line;
    private final Label label;

    private final Vertex sourceVertex;
    private Vertex targetVertex;

    private String identifier = " ";

    public Edge(Vertex sourceVertex, double endX, double endY) {
        this.sourceVertex = sourceVertex;

        line = new Line();
        label = new Label();
        group = new Group();

        setLineParams();
        setLabelParams();
        addElementsToGroup();

        updateLinePosition(sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY(), endX, endY);
    }



//    private  void createEdgeElements() {
//
//    }

    private void addElementsToGroup() {
        group.getChildren().addAll(line, label);
    }

    private void setLineParams() {
        line.setCursor(Cursor.HAND);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(LINE_WIDTH);
    }

    private void setLabelParams() {
        label.setFont(new Font(FONT_TYPE, FONT_SIZE));
    }

    private void updateLinePosition(double startX, double startY, double endX, double endY) {
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
    }

    private void updateLabelPosition() {
        label.relocate((targetVertex.getCircleCenterX() + sourceVertex.getCircleCenterX())/2.0,
                (targetVertex.getCircleCenterY() + sourceVertex.getCircleCenterY())/2.0 + 10);
    }



    public Group getGroup() {
        return group;
    }

    public void updateEdgePosition() {
        updateLabelPosition();
        updateLinePosition(sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY(),
                    targetVertex.getCircleCenterX(), targetVertex.getCircleCenterY());
    }

    public void setIdentifier(String identifier) {
        if(!identifier.equals("")){
            this.identifier = identifier;
            label.setText(identifier);
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
        updateEdgePosition();
    }

    public void setTargetPosition(double endX, double endY) {
        updateLinePosition(sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY(), endX, endY);
    }

    public void makeActive() {
        line.setStroke(Color.GREEN);
    }

    public void makeInactive() {
        line.setStroke(Color.BLACK);
    }

    public void addEventHandler(EventType<MouseEvent> eventType, EventHandler<MouseEvent> eventHandler) {
        line.addEventHandler(eventType, eventHandler);
    }

    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

}
