package com.graph.editor.model;

import com.graph.editor.view.shapes.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final List<VertexAndAdjacentVertices> graphList;

    public Graph() {
        graphList = new ArrayList<>();
    }

    public void addVertexToGraph(Vertex vertex) {
        graphList.add(new VertexAndAdjacentVertices(vertex));
    }

    public void addAdjacentVertex(Vertex sourceVertex, Vertex targetVertex) {
        for (VertexAndAdjacentVertices vertexAndAdjacentVertices : graphList) {
            if (vertexAndAdjacentVertices.getVertex() == sourceVertex) {
                vertexAndAdjacentVertices.getAdjacentVertices().add(targetVertex);
            }
        }
    }

}
