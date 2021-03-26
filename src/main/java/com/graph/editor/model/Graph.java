package com.graph.editor.model;

import com.graph.editor.view.shapes.NotOrientedEdge;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Graph {

    private final List<VertexAndAdjacentVertices> graphList;
    private final List<NotOrientedEdge> notOrientedEdgeList;

    public Graph() {
        notOrientedEdgeList = new ArrayList<>();
        graphList = new ArrayList<>();
    }

    public List<VertexAndAdjacentVertices> getGraphList() {
        return graphList;
    }

    public List<NotOrientedEdge> getEdgeList() {
        return notOrientedEdgeList;
    }

    public Vertex getVertexByGroup(Group group) {
        return graphList
                .stream()
                .map(VertexAndAdjacentVertices::getVertex)
                .filter(vertex -> vertex.getGroup() == group)
                .findFirst()
                .orElse(null);
    }

    public List<NotOrientedEdge> getListOfConnectedEdges(Vertex vertex) {
        return notOrientedEdgeList
                .stream()
                .filter(notOrientedEdge -> notOrientedEdge.getSourceVertex() == vertex || notOrientedEdge.getTargetVertex() == vertex)
                .collect(Collectors.toList());
    }

    public void addEdge(NotOrientedEdge notOrientedEdge) {
        for (VertexAndAdjacentVertices vertexAndAdjacentVertices : graphList) {
            if (vertexAndAdjacentVertices.getVertex() == notOrientedEdge.getSourceVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().add(notOrientedEdge.getTargetVertex());
            }
            else if (vertexAndAdjacentVertices.getVertex() == notOrientedEdge.getTargetVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().add(notOrientedEdge.getSourceVertex());
            }
        }
        notOrientedEdgeList.add(notOrientedEdge);
    }

    public void removeEdge(NotOrientedEdge notOrientedEdge) {
        for(VertexAndAdjacentVertices vertexAndAdjacentVertices : graphList) {
            if(vertexAndAdjacentVertices.getVertex() == notOrientedEdge.getSourceVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().remove(notOrientedEdge.getTargetVertex());
            }
            else if(vertexAndAdjacentVertices.getVertex() == notOrientedEdge.getTargetVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().remove(notOrientedEdge.getSourceVertex());
            }
        }
        notOrientedEdgeList.remove(notOrientedEdge);
    }

    public void addVertexToGraph(Vertex vertex) {
        graphList.add(new VertexAndAdjacentVertices(vertex));
    }

    public void removeVertex(Vertex vertex) {
        getListOfConnectedEdges(vertex)
                .forEach(this::removeEdge);

        graphList
                .removeIf(vertexAndAdjacentVertices -> vertexAndAdjacentVertices.getVertex() == vertex);
    }

    public void clear() {
        notOrientedEdgeList.clear();
        graphList.clear();
    }

    public int getIndexOfVertex(Vertex vertex) {
        for(int i = 0; i < graphList.size(); i++) {
            if(graphList.get(i).getVertex() == vertex) {
                return i;
            }
        }
        return 0;
    }

}
