package com.graph.editor.controller;

import com.graph.editor.model.Graph;
import com.graph.editor.model.GraphListElement;
import com.graph.editor.model.SelectedElement;
import com.graph.editor.model.graph_task.GraphTask;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import java.util.List;

public class GraphTaskController {

    private final Graph graph;

    private final List<List<Vertex>> hamiltonsCycles;

    public GraphTaskController(Graph graph) {
        this.graph = graph;

        GraphTask graphTask = new GraphTask(graph);
        graphTask.findHamiltonsCycles();
        hamiltonsCycles = graphTask.getHamiltonsCycles();
    }

    public void startGraphTask() {
         new Thread(() -> {
             deselectAllElements();
            for(List<Vertex> hamiltonCycle : hamiltonsCycles) {
                for (Vertex vertex : hamiltonCycle) {
                    vertex.makeActive();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                deselectAllElements();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void deselectAllElements() {
        graph.getGraphList()
                .stream()
                .map(GraphListElement::getVertex)
                .forEach(Vertex::makeInactive);
    }

}
