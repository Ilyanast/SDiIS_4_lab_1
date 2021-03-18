package com.graph.editor.model;

import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Graph {

    //TODO Поюзать stream API

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

    public List<Edge> getListOfConnectedEdges(Vertex vertex) {
        return edgeList
                .stream()
                .filter(edge -> edge.getSourceVertex() == vertex || edge.getTargetVertex() == vertex)
                .collect(Collectors.toList());
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

    public void removeEdge(Edge edge) {
        for(VertexAndAdjacentVertices vertexAndAdjacentVertices : graphList) {
            if(vertexAndAdjacentVertices.getVertex() == edge.getSourceVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().remove(edge.getTargetVertex());
            }
            else if(vertexAndAdjacentVertices.getVertex() == edge.getTargetVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().remove(edge.getSourceVertex());
            }
        }
        edgeList.remove(edge);
    }

    public void addVertexToGraph(Vertex vertex) {
        graphList.add(new VertexAndAdjacentVertices(vertex));
    }

    public void removeVertex(Vertex vertexToDelete) {
        List<Edge> connectedEdges = getListOfConnectedEdges(vertexToDelete);
        for (Edge connectedEdge : connectedEdges) {
            removeEdge(connectedEdge);
        }
        graphList.removeIf(vertexAndAdjacentVertices -> vertexAndAdjacentVertices.getVertex() == vertexToDelete);
    }
}
