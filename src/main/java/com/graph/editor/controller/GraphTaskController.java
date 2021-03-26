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

    private final Pane pane;
    private final Graph graph;
    private final SelectedElement selectedElement;

    private final List<List<Vertex>> hamiltonsCycles;

    public GraphTaskController(Pane pane, Graph graph, SelectedElement selectedElement) {
        this.selectedElement = selectedElement;
        this.graph = graph;
        this.pane = pane;

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
