package com.graph.editor.view.shapes;

import com.graph.editor.model.Selectable;
import javafx.event.EventHandler;
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

    //TODO Пофиксить дугу к самому себе

    private static final int LINE_WIDTH = 5;

    private final Group group;
    private final Line line;
    private final Label label;

    private Vertex sourceVertex;
    private Vertex targetVertex;



    public Edge(Vertex sourceVertex, Vertex targetVertex) {
        setVertices(sourceVertex, targetVertex);

        line = new Line();
        label = new Label();
        group = new Group();

        setLineParams();
        setLabelParams();
        addElementsToGroup();
    }



    private void setVertices(Vertex sourceVertex, Vertex targetVertex) {
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
    }

    private void addElementsToGroup() {
        group.getChildren().addAll(line, label);
    }

    private void setLineParams() {
        updateEdgePosition();
        line.setCursor(Cursor.HAND);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(LINE_WIDTH);
    }

    private void setLabelParams() {
        updateLabelPosition();
        label.setFont(new Font(FONT_TYPE, FONT_SIZE));
    }

    private void updateLinePosition() {
        line.setStartX(sourceVertex.getCircleCenterX());
        line.setStartY(sourceVertex.getCircleCenterY());
        line.setEndX(targetVertex.getCircleCenterX());
        line.setEndY(targetVertex.getCircleCenterY());
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
        updateLinePosition();
    }

    public void setIdentifier(String identifier) {
        if(!identifier.equals("")){
            label.setText(identifier);
        }
    }

    public void makeActive() {
        line.setStroke(Color.GREEN);
    }

    public void makeInactive() {
        line.setStroke(Color.BLACK);
    }

    public void setOnMouseClicked(EventHandler<MouseEvent> mouseEvent) {
        line.setOnMouseClicked(mouseEvent);
    }

    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

}
