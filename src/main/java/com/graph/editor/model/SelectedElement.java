package com.graph.editor.model;

public class SelectedElement {

    private Selectable selectedElement;

    public Selectable getSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(Selectable selectedElement) {
        deselectElement();
        selectedElement.makeActive();
        this.selectedElement = selectedElement;
    }

    public void deselectElement() {
        if (selectedElement != null) {
            selectedElement.makeInactive();
            selectedElement = null;
        }
    }

    public boolean isElementSelected() {
        return selectedElement != null;
    }

}
