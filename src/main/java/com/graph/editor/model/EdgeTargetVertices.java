package com.graph.editor.model;

import com.graph.editor.view.shapes.Vertex;

public class EdgeTargetVertices {

    private Vertex firstVertex;

    public Vertex getFirstVertex() {
        return firstVertex;
    }

    public void setFirstVertex(Vertex firstVertex) {
        this.firstVertex = firstVertex;
    }

    public boolean isWaitForSecondClick() {
        return firstVertex != null;
    }

    public void clear() {
        firstVertex = null;
    }

}
