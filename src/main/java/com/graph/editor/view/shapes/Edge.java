package com.graph.editor.view.shapes;

import com.graph.editor.model.Selectable;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import static com.graph.editor.model.Parameters.*;

public abstract class Edge implements Selectable {

    protected Group group;
    protected Line line;
    protected Label label;

    protected final Vertex sourceVertex;
    protected Vertex targetVertex;

    private String identifier = " ";


    public Edge(Vertex sourceVertex) {
        this.sourceVertex = sourceVertex;

        createBasicElements();

        setLabelParams();
        setLineParams(line);

        updateLinePosition(line, sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY(),
                sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY());
    }


    public Group getGroup() {
        return group;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        if(!identifier.equals("")){
            this.identifier = identifier;
            label.setText(identifier);
        }
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
        updateEdgePosition();
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


    public abstract void setTargetPosition(double endX, double endY);

    public abstract void updateEdgePosition();

    public abstract void makeInactive();

    public abstract void makeActive();


    protected void setLineParams(Line line) {
        line.setCursor(Cursor.HAND);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(LINE_WIDTH);
    }

    protected void updateLinePosition(Line line, double startX, double startY, double endX, double endY) {
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
    }

    protected void updateLabelPosition() {
        label.relocate((targetVertex.getCircleCenterX() + sourceVertex.getCircleCenterX())/2.0,
                (targetVertex.getCircleCenterY() + sourceVertex.getCircleCenterY())/2.0 + 10);
    }


    private void createBasicElements() {
        line = new Line();
        label = new Label();
        group = new Group(label, line);
    }

    private void setLabelParams() {
        label.setFont(new Font(FONT_TYPE, FONT_SIZE));
    }


}
