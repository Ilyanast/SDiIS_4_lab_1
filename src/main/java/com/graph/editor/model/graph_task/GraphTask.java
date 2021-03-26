package com.graph.editor.model.graph_task;

import com.graph.editor.model.Graph;
import com.graph.editor.model.GraphListElement;
import com.graph.editor.view.shapes.Vertex;

import java.util.ArrayList;
import java.util.List;

public class GraphTask {

    private final List<GraphListElement> graphList;
    private final List<List<Vertex>> hamiltonsCycles;
    private final Graph graph;


    public GraphTask(Graph graph) {
        this.graph = graph;

        graphList = graph.getGraphList();

        hamiltonsCycles = new ArrayList<>();
    }

    public void findHamiltonsCycles() {
        List<Vertex> visitedVertices = new ArrayList<>();

        for (GraphListElement graphListElement : graphList) {
            visitedVertices.add(graphListElement.getVertex());
            depthFirstSearch(visitedVertices, graphListElement, graphListElement);
            visitedVertices.clear();
        }
    }

    public List<List<Vertex>> getHamiltonsCycles() {
        return hamiltonsCycles;
    }

    private void depthFirstSearch(List<Vertex> visitedVertices, GraphListElement currentVertex, GraphListElement sourceVertex) {
        for (int i = 0; i < currentVertex.getAdjacentVertices().size(); i++) {
            if (!isInList(visitedVertices, currentVertex.getAdjacentVertices().get(i))) {
                visitedVertices.add(currentVertex.getAdjacentVertices().get(i));
                depthFirstSearch(visitedVertices, graphList.get(graph.getIndexOfVertex(currentVertex.getAdjacentVertices().get(i))) , sourceVertex);
                visitedVertices.remove(visitedVertices.size() - 1);
            } else if (isInList(currentVertex.getAdjacentVertices(), sourceVertex.getVertex()) && visitedVertices.size() == graphList.size()) {
                List<Vertex> hamiltonCycle = new ArrayList<>(visitedVertices);
                hamiltonsCycles.add(hamiltonCycle);
                break;
            }
        }
    }

    private boolean isInList(List<Vertex> visitedVertex, Vertex vertexToSearch) {
        for(Vertex vertex : visitedVertex) {
            if(vertex == vertexToSearch) {
                return true;
            }
        }
        return false;
    }

}
