package com.graph.editor.model;

import com.graph.editor.view.shapes.Vertex;

public class CurrentActiveElement {

    //TODO Make active element instead of vertex

    private Vertex currentActiveVertex;
    private final Vertex inactiveVertex;

    public CurrentActiveElement() {
        inactiveVertex = new Vertex(0, 0);
        currentActiveVertex = inactiveVertex;
    }

    public Vertex getCurrentActiveVertex() {
        return currentActiveVertex;
    }

    public void selectActiveVertex(Vertex newActiveVertex) {
        currentActiveVertex.makeVertexInactive();
        currentActiveVertex = newActiveVertex;
        newActiveVertex.makeVertexActive();
    }

    public void unselectAllVertices() {
        selectActiveVertex(inactiveVertex);
    }

    public boolean isAnyVertexActive() {
        return currentActiveVertex != inactiveVertex;
    }

}
