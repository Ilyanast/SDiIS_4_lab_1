package com.graph.editor.model;

import com.graph.editor.controller.ToolType;

public class CurrentTool {

    private ToolType currentToolType;

    public CurrentTool() {
        currentToolType = ToolType.HAND_TOOL;
    }

    public ToolType getCurrentTool() {
        return currentToolType;
    }

    public void setCurrentTool(ToolType currentToolType) {
        this.currentToolType = currentToolType;
    }
}
