package com.graph.editor.model;

import com.graph.editor.view.shapes.Vertex;

public class CurrentActiveVertex {

    private Vertex currentActiveVertex;

    public CurrentActiveVertex() {
        currentActiveVertex = new Vertex(0, 0);
    }

    public Vertex getCurrentActiveVertex() {
        return currentActiveVertex;
    }

    public void setCurrentActiveVertex(Vertex newActiveVertex) {
        currentActiveVertex.makeVertexInactive();
        currentActiveVertex = newActiveVertex;
        newActiveVertex.makeVertexActive();
    }

}
