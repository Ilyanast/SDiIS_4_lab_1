package com.graph.editor.view.shapes;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class Vertex {

    private static final int CIRCLE_RADIUS = 10;
    private static final int CIRCLE_STROKE_WIDTH = 3;
    private static final int FONT_SIZE = 14;

    private final Group group;
    private final Circle circle;
    private final Label label;

    private double x_pos;
    private double y_pos;

    public Vertex(double x_pos, double y_pos) {
        setPosition(x_pos, y_pos);

        circle = new Circle(x_pos, y_pos, CIRCLE_RADIUS);
        group = new Group();
        label = new Label();

        addElementsToGroup();
        setCircleParams();
        setLabelParams();
        setIdentifier("Test");
    }

    private void setPosition(double x_pos, double y_pos) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }

    private void addElementsToGroup() {
        group.getChildren().addAll(circle, label);
    }

    private void setLabelParams() {
        label.relocate(x_pos + CIRCLE_RADIUS, y_pos + CIRCLE_RADIUS);
        label.setFont(new Font("Times New Roman", FONT_SIZE));
    }

    private void setCircleParams() {
        circle.setFill(Color.WHITE);
        circle.setCursor(Cursor.HAND);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(CIRCLE_STROKE_WIDTH);
    }

    public void setNewPos() {
        setPosition(x_pos + circle.getTranslateX(), y_pos + circle.getTranslateY());

        circle.setCenterX(x_pos);
        circle.setCenterY(y_pos);
        circle.setTranslateX(0);
        circle.setTranslateY(0);

        label.relocate(x_pos + CIRCLE_RADIUS, y_pos + CIRCLE_RADIUS);
        label.setTranslateX(0);
        label.setTranslateY(0);
    }

    public Group getGroup() {
        return group;
    }

    public void makeVertexActive() {
        circle.setStroke(Color.GREEN);
    }

    public void makeVertexInactive() {
        circle.setStroke(Color.BLACK);
    }

    public void setIdentifier(String identifier) {
        label.setText(identifier);
    }

    public double getTranslateX() {
        return circle.getTranslateX();
    }

    public double getTranslateY() {
        return circle.getTranslateY();
    }

    public void setTranslateX(double translateX) {
        circle.setTranslateX(translateX);
        label.setTranslateX(translateX);
    }

    public void setTranslateY(double translateY) {
        circle.setTranslateY(translateY);
        label.setTranslateY(translateY);
    }

    public void setOnMouseReleased(EventHandler<MouseEvent> mouseevent) {
        circle.setOnMouseReleased(mouseevent);
    }

    public void setOnMouseDragged(EventHandler<MouseEvent> mouseEvent) {
        circle.setOnMouseDragged(mouseEvent);
    }

    public void setOnMousePressed(EventHandler<MouseEvent> mouseEvent) {
        circle.setOnMousePressed(mouseEvent);
    }

}
