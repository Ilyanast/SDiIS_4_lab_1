package com.graph.editor.model;

import com.graph.editor.controller.Tool;

public class CurrentTool {

    private Tool currentTool;

    public CurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    public Tool getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }
}
