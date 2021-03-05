package com.graph.editor.model;

public class ElementsSizeParams {

    private int sceneSizeX, sceneSizeY;
    private int canvasSizeX, canvasSizeY;
    private int toolBarWidth;

    public ElementsSizeParams() {
        SizeParamsReader paramsReader = new SizeParamsReader();
        readAllParams(paramsReader.getAllParams());
    }

    private void readAllParams(int[] allParams){
        sceneSizeX = allParams[0];
        sceneSizeY = allParams[1];
        canvasSizeX = allParams[2];
        canvasSizeY = allParams[3];
        toolBarWidth = allParams[4];
    }

    public void setSceneSize(int sceneSizeX, int sceneSizeY){
        this.sceneSizeX = sceneSizeX;
        this.sceneSizeY = sceneSizeY;
    }

    public void setCanvasSize(int canvasSizeX, int canvasSizeY){
        this.canvasSizeX = canvasSizeX;
        this.canvasSizeY = canvasSizeY;
    }

    public int getSceneSizeX() {
        return sceneSizeX;
    }

    public int getSceneSizeY() {
        return sceneSizeY;
    }

    public int getCanvasSizeX() {
        return canvasSizeX;
    }

    public int getCanvasSizeY() {
        return canvasSizeY;
    }

    public int getToolBarWidth() {
        return toolBarWidth;
    }
}
