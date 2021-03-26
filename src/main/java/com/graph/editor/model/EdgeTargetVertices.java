package com.graph.editor.model;

import com.graph.editor.view.shapes.*;

public class EdgeTargetVertices {

    private Vertex sourceVertex;
    private Edge edge;


    public void setSourceVertex(Vertex sourceVertex, EdgeType edgeType) {
        this.sourceVertex = sourceVertex;

        switch (edgeType) {
            case ORIENTED_EDGE: {
                edge = new OrientedEdge(sourceVertex);
                edge.setEdgeType(edgeType);
            }
            break;
            case NOT_ORIENTED_EDGE: {
                edge = new NotOrientedEdge(sourceVertex);
                edge.setEdgeType(edgeType);
                break;
            }
        }

    }

    public void setTargetVertex(Vertex targetVertex) {
        edge.setTargetVertex(targetVertex);
    }

    public void setEndLinePosition(double endX, double endY) {
        edge.setTargetPosition(endX, endY);
    }

    public boolean isWaitForTargetVertex() {
        return sourceVertex != null;
    }

    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    public Edge getEdge() {
        return edge;
    }

    public void clear() {
        sourceVertex = null;
        edge = null;
    }

}
