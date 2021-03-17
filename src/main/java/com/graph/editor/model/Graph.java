package com.graph.editor.model;

import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final List<VertexAndAdjacentVertices> graphList;
    private final List<Edge> edgeList;

    public Graph() {
        edgeList = new ArrayList<>();
        graphList = new ArrayList<>();
    }

    public Vertex getVertexByGroup(Group group) {
        for (VertexAndAdjacentVertices vertexAndAdjacentVertices : graphList) {
            if (vertexAndAdjacentVertices.getVertex().getGroup() == group) {
                return vertexAndAdjacentVertices.getVertex();
            }
        }
        return null;
    }

    public void addEdge(Edge edge) {
        for (VertexAndAdjacentVertices vertexAndAdjacentVertices : graphList) {
            if (vertexAndAdjacentVertices.getVertex() == edge.getSourceVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().add(edge.getTargetVertex());
            }
            else if (vertexAndAdjacentVertices.getVertex() == edge.getTargetVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().add(edge.getSourceVertex());
            }
        }
        edgeList.add(edge);
    }

    public void addVertexToGraph(Vertex vertex) {
        graphList.add(new VertexAndAdjacentVertices(vertex));
    }

    public void removeVertex(Vertex vertexToDelete) {
        graphList.removeIf(vertexAndAdjacentVertices -> vertexAndAdjacentVertices.getVertex() == vertexToDelete);
    }
}
