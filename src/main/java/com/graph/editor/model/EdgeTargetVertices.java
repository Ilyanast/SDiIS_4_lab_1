package com.graph.editor.model;

import com.graph.editor.view.shapes.NotOrientedEdge;
import com.graph.editor.view.shapes.Vertex;

public class EdgeTargetVertices {

    private Vertex sourceVertex;
    private NotOrientedEdge notOrientedEdge;

    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    public void setSourceVertex(Vertex sourceVertex) {
        this.sourceVertex = sourceVertex;
        this.notOrientedEdge = new NotOrientedEdge(sourceVertex, sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY());
    }

    public void setTargetVertex(Vertex targetVertex) {
        notOrientedEdge.setTargetVertex(targetVertex);
    }

    public boolean isWaitForSecondClick() {
        return sourceVertex != null;
    }

    public NotOrientedEdge getEdge() {
        return notOrientedEdge;
    }

    public void clear() {
        sourceVertex = null;
        notOrientedEdge = null;
    }

    public void setLineEndPosition(double endX, double endY) {
        notOrientedEdge.setTargetPosition(endX, endY);
    }

}
