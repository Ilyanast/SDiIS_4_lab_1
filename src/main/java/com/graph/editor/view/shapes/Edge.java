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

public class Edge implements Selectable {

    //TODO Пофиксить label;
    //TODO Пофиксить дугу к самому себе

    private static final int FONT_SIZE = 14;
    private static final int LINE_WIDTH = 5;

    private Group group;
    private Label label;
    private Line line;

    private final Vertex sourceVertex;
    private final Vertex targetVertex;


    public Edge(Vertex sourceVertex, Vertex targetVertex) {
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;

        createAllElements();
        addElementsToGroup();
        setLineParams();
        setLabelParams();
    }


    private  void createAllElements(){
        line = new Line();
        label = new Label();
        group = new Group();
    }

    private void addElementsToGroup() {
        group.getChildren().addAll(line, label);
    }

    private void updateLineCoords() {
        line.setStartX(sourceVertex.getCircleCenterX() + sourceVertex.getCircleTranslateX());
        line.setStartY(sourceVertex.getCircleCenterY() + sourceVertex.getCircleTranslateY());
        line.setEndX(targetVertex.getCircleCenterX() + targetVertex.getCircleTranslateX());
        line.setEndY(targetVertex.getCircleCenterY() + targetVertex.getCircleTranslateY());
    }

    private void updateLabelCoords() {

        //TODO Сделать if circleTR == 0 у конкретной вершины, чтобы сделать измененние положения у 2х вершин.

        double x_pos = (targetVertex.getCircleCenterX() + targetVertex.getCircleTranslateX() -
                            sourceVertex.getCircleCenterX() + sourceVertex.getCircleTranslateY())/2.0 +
                                sourceVertex.getCircleCenterX() + sourceVertex.getCircleTranslateX();

        double y_pos = (targetVertex.getCircleCenterY() + targetVertex.getCircleTranslateY() -
                            sourceVertex.getCircleCenterY() + sourceVertex.getCircleTranslateY())/2.0 + 5 +
                                sourceVertex.getCircleCenterY() + sourceVertex.getCircleTranslateY();

        label.relocate(x_pos, y_pos);
    }

    private void setLabelParams() {
        label.relocate((targetVertex.getCircleCenterX() - sourceVertex.getCircleCenterX())/2.0 + sourceVertex.getCircleCenterX(),
                (targetVertex.getCircleCenterY() - sourceVertex.getCircleCenterY())/2.0 + 5 + sourceVertex.getCircleCenterY());
        label.setFont(new Font("Times New Roman", FONT_SIZE));
    }

    private void setLineParams() {
        updateEdgeCoords();
        line.setCursor(Cursor.HAND);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(LINE_WIDTH);
    }

    public void updateEdgeCoords() {
        updateLabelCoords();
        updateLineCoords();
    }

    public void setIdentifier(String identifier) {
        if(!identifier.equals("")){
            label.setText(identifier);
        }
    }

    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
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

    public Group getGroup() {
        return group;
    }

}
