package com.graph.editor.view.shapes;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class Edge {

    //TODO Сделать setOnCLICK и makeActive
    //TODO Придумать, что как получить из обновить из VertexController координаты

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

    private void setLabelParams() {
        label.relocate((targetVertex.getCircleCenterX() - sourceVertex.getCircleCenterX())/2.0 + sourceVertex.getCircleCenterX(),
                (targetVertex.getCircleCenterY() - sourceVertex.getCircleCenterY())/2.0 + 5 + sourceVertex.getCircleCenterY());
        label.setFont(new Font("Times New Roman", FONT_SIZE));
    }

    private void setLineParams() {
        updateLineCoords();
        line.setCursor(Cursor.HAND);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(LINE_WIDTH);
    }

    public void updateLineCoords() {
        line.setStartX(sourceVertex.getCircleCenterX() + sourceVertex.getCircleTranslateX());
        line.setStartY(sourceVertex.getCircleCenterY() + sourceVertex.getCircleTranslateY());
        line.setEndX(targetVertex.getCircleCenterX() + targetVertex.getCircleTranslateX());
        line.setEndY(targetVertex.getCircleCenterY() + targetVertex.getCircleTranslateY());
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

    public Group getGroup() {
        return group;
    }

}
