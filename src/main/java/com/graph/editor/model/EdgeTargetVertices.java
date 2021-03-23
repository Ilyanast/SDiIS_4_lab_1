package com.graph.editor.model;

import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.Vertex;

public class EdgeTargetVertices {

    private Vertex sourceVertex;
    private Edge edge;

    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    public void setSourceVertex(Vertex sourceVertex) {
        this.sourceVertex = sourceVertex;
        this.edge = new Edge(sourceVertex, sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY());
    }

    public void setTargetVertex(Vertex targetVertex) {
        edge.setTargetVertex(targetVertex);
    }

    public boolean isWaitForSecondClick() {
        return sourceVertex != null;
    }

    public Edge getEdge() {
        return edge;
    }

    public void clear() {
        sourceVertex = null;
        edge = null;
    }

    public void setLineEndPosition(double endX, double endY) {
        edge.setTargetPosition(endX, endY);
    }

}
