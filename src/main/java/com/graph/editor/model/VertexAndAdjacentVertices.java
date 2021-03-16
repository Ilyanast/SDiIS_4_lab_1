package com.graph.editor.model;

import com.graph.editor.view.shapes.Vertex;

import java.util.ArrayList;
import java.util.List;

public class VertexAndAdjacentVertices {

    private final Vertex vertex;
    private final List<Vertex> adjacentVertices;

    public VertexAndAdjacentVertices(Vertex vertex) {
        adjacentVertices = new ArrayList<Vertex>();
        this.vertex = vertex;
    }

    public List<Vertex> getAdjacentVertices() {
        return adjacentVertices;
    }

    public Vertex getVertex() {
        return vertex;
    }

}
