package com.graph.editor.model;

import com.graph.editor.view.shapes.Vertex;

public class EdgeTargetVertices {

    private Vertex firstVertex;
    private Vertex secondVertex;

    public Vertex getFirstVertex() {
        return firstVertex;
    }

    public void setFirstVertex(Vertex firstVertex) {
        this.firstVertex = firstVertex;
    }

    public Vertex getSecondVertex() {
        return secondVertex;
    }

//    public void setSecondVertex(Vertex secondVertex) {
//        this.secondVertex = secondVertex;
//    }

    public boolean isWaitForSecondClick() {
        if(firstVertex != null && secondVertex == null) {
            return true;
        }
        else if(firstVertex == null) {
            return false;
        }
        else {
            return false;
        }
    }

    public void clear() {
        firstVertex = null;
        secondVertex = null;
    }

}
