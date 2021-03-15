package com.graph.editor.view.shapes;

import javafx.scene.Group;
import javafx.scene.control.Label;
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

    private final double x_pos;
    private final double y_pos;

    public Vertex(double x_pos, double y_pos) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;

        circle = new Circle(x_pos, y_pos, CIRCLE_RADIUS);
        group = new Group();
        label = new Label();

        addElementsToGroup();
        setCircleParams();
        setLabelParams();
    }

    private void setLabelParams() {
        label.relocate(x_pos + CIRCLE_RADIUS, y_pos + CIRCLE_RADIUS);
        label.setFont(new Font("Times New Roman", FONT_SIZE));
    }

    private void addElementsToGroup() {
        group.getChildren().addAll(circle, label);
    }

    private void setCircleParams() {
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(CIRCLE_STROKE_WIDTH);
    }

    public void setIdentifier(String identifier) {
        label.setText(identifier);
    }

    public Group getGroup() {
        return group;
    }
}
