package com.graph.editor.model;

public class MainWindowParams {

    private int sceneSizeX, sceneSizeY;
    private int canvasSizeX, canvasSizeY;

    public MainWindowParams() {
        ParamsReader paramsReader = new ParamsReader("params.txt");
        loadAllParams(paramsReader.getAllParams());
    }

    private void loadAllParams(int[] allParams){
        sceneSizeX = allParams[0];
        sceneSizeY = allParams[1];
        canvasSizeX = allParams[2];
        canvasSizeY = allParams[3];
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
}
