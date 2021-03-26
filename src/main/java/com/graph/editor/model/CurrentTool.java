package com.graph.editor.model;

public class CurrentTool {

    private ToolType currentToolType;


    public CurrentTool() {
        currentToolType = ToolType.HAND_TOOL;
    }


    public void setCurrentTool(ToolType currentToolType) {
        this.currentToolType = currentToolType;
    }

    public ToolType getCurrentTool() {
        return currentToolType;
    }
}
