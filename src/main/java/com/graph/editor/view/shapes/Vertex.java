package com.graph.editor.view.shapes;

import com.graph.editor.model.Selectable;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class Vertex implements Selectable {

    //TODO УБрать translation

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
    }

    private void setPosition(double new_x_pos, double new_y_pos) {
        x_pos = new_x_pos;
        y_pos = new_y_pos;
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

    public void updatePositionWithTranslate() {
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

    public void makeActive() {
        circle.setStroke(Color.GREEN);
    }

    public void makeInactive() {
        circle.setStroke(Color.BLACK);
    }

    public void setIdentifier(String identifier) {
        if(!identifier.equals("")){
            label.setText(identifier);
        }
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

    public double getCircleCenterX() {
        return circle.getCenterX();
    }

    public double getCircleCenterY() {
        return circle.getCenterY();
    }

    public double getCircleTranslateX() {
        return circle.getTranslateX();
    }

    public double getCircleTranslateY() {
        return circle.getTranslateY();
    }


}
