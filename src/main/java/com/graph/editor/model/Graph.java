package com.graph.editor.model;

import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.EdgeType;
import com.graph.editor.view.shapes.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Graph {

    private final List<GraphListElement> graphList;
    private final List<Edge> edgeList;


    public Graph() {
        edgeList = new ArrayList<>();
        graphList = new ArrayList<>();
    }


    public List<GraphListElement> getGraphList() {
        return graphList;
    }

    public List<Edge> getListOfConnectedEdges(Vertex vertex) {
        return edgeList
                .stream()
                .filter(edge -> edge.getSourceVertex() == vertex || edge.getTargetVertex() == vertex)
                .collect(Collectors.toList());
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public int getIndexOfVertex(Vertex vertex) {
        for(int i = 0; i < graphList.size(); i++) {
            if(graphList.get(i).getVertex() == vertex) {
                return i;
            }
        }
        return 0;
    }

    public void removeVertex(Vertex vertex) {
        getListOfConnectedEdges(vertex)
                .forEach(this::removeEdge);

        graphList
                .removeIf(vertexAndAdjacentVertices -> vertexAndAdjacentVertices.getVertex() == vertex);
    }

    public void addVertex(Vertex vertex) {
        graphList.add(new GraphListElement(vertex));
    }

    public void removeEdge(Edge edge) {
        if(isEdgeExist(edge, edge.getEdgeType())) {
            switch (edge.getEdgeType()) {
                case ORIENTED_EDGE: {
                    removeOrientedEdge(edge);
                    break;
                }
                case NOT_ORIENTED_EDGE: {
                    removeNotOrientedEdge(edge);
                    break;
                }
            }
            edgeList.remove(edge);
        }
    }

    public boolean addEdge(Edge edge, EdgeType edgeType) {
        if(!isEdgeExist(edge, edgeType)) {
            switch (edgeType) {
                case ORIENTED_EDGE: {
                    addOrientedEdge(edge);
                    break;
                }
                case NOT_ORIENTED_EDGE: {
                    addNotOrientedEdge(edge);
                    break;
                }
            }
            edgeList.add(edge);
            return true;
        }
        return false;
    }

    public void clear() {
        edgeList.clear();
        graphList.clear();
    }


    private void addOrientedEdge(Edge edge) {
        for (GraphListElement vertexAndAdjacentVertices : graphList) {
            if(vertexAndAdjacentVertices.getVertex() == edge.getSourceVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().add(edge.getTargetVertex());
                break;
            }
        }
    }

    private void addNotOrientedEdge(Edge edge) {
        for(GraphListElement vertexAndAdjacentVertices : graphList) {
            if(vertexAndAdjacentVertices.getVertex() == edge.getSourceVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().add(edge.getTargetVertex());
            }
            else if (vertexAndAdjacentVertices.getVertex() == edge.getTargetVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().add(edge.getSourceVertex());
            }
        }
    }

    private void removeOrientedEdge(Edge edge) {
        for(GraphListElement vertexAndAdjacentVertices : graphList) {
            if (vertexAndAdjacentVertices.getVertex() == edge.getSourceVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().remove(edge.getTargetVertex());
                break;
            }
        }
    }

    private void removeNotOrientedEdge(Edge edge) {
        for(GraphListElement vertexAndAdjacentVertices : graphList) {
            if(vertexAndAdjacentVertices.getVertex() == edge.getSourceVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().remove(edge.getTargetVertex());
            }
            else if(vertexAndAdjacentVertices.getVertex() == edge.getTargetVertex()) {
                vertexAndAdjacentVertices.getAdjacentVertices().remove(edge.getSourceVertex());
            }
        }
    }

    private boolean isEdgeExist(Edge edge, EdgeType edgeType) {
        for (Edge edgeListElement : edgeList) {
            switch (edgeType) {
                case ORIENTED_EDGE: {
                    if ((edgeListElement.getSourceVertex() == edge.getSourceVertex() && edgeListElement.getTargetVertex() == edge.getTargetVertex())) {
                        return true;
                    }
                    break;
                }
                case NOT_ORIENTED_EDGE: {
                    if ((edgeListElement.getSourceVertex() == edge.getSourceVertex() && edgeListElement.getTargetVertex() == edge.getTargetVertex()) ||
                            (edgeListElement.getTargetVertex() == edge.getSourceVertex() && edgeListElement.getSourceVertex() == edge.getTargetVertex())) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
}
