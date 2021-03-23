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

import static com.graph.editor.model.Parameters.*;

public class Vertex implements Selectable {

    private static final int CIRCLE_STROKE_WIDTH = 3;

    private final Group group;
    private final Circle circle;
    private final Label label;

    private double x_pos;
    private double y_pos;



    public Vertex(double x_pos, double y_pos) {
        setPosition(x_pos, y_pos);

        circle = new Circle(CIRCLE_RADIUS);
        group = new Group();
        label = new Label();

        setCircleParams();
        setLabelParams();
        addElementsToGroup();
    }



    private void setPosition(double x_pos, double y_pos) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }

    private void addElementsToGroup() {
        group.getChildren().addAll(circle, label);
    }

    private void setCircleParams() {
        updateCirclePosition();
        circle.setFill(Color.WHITE);
        circle.setCursor(Cursor.HAND);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(CIRCLE_STROKE_WIDTH);
    }

    private void setLabelParams() {
        updateLabelPosition();
        label.setFont(new Font(FONT_TYPE, FONT_SIZE));
    }

    private void updateCirclePosition() {
        circle.setCenterX(x_pos);
        circle.setCenterY(y_pos);
    }

    private void updateLabelPosition() {
        label.relocate(x_pos + CIRCLE_RADIUS, y_pos + CIRCLE_RADIUS);
    }



    public Group getGroup() {
        return group;
    }

    public void setVertexPosition(double x_pos, double y_pos) {
        setPosition(x_pos, y_pos);
        updateCirclePosition();
        updateLabelPosition();
    }

    public void setIdentifier(String identifier) {
        if(!identifier.equals("")){
            label.setText(identifier);
        }
    }

    public void makeActive() {
        circle.setStroke(Color.GREEN);
    }

    public void makeInactive() {
        circle.setStroke(Color.BLACK);
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

}
