package com.graph.editor.view;

import javafx.scene.control.Alert;

public class InformationDialogBuilder {

    private static final String DIALOG_TITLE = "SDiIS Graph Editor";
    private static final String DIALOG_CONTENT_TEXT = "Developed by Nastachkin Ilya Sergeevich. Group 921702.";

    Alert alert;

    public InformationDialogBuilder() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        setInformationDialogParams();
    }

    private void setInformationDialogParams() {
        alert.setContentText(DIALOG_CONTENT_TEXT);
        alert.setTitle(DIALOG_TITLE);
        alert.setHeaderText(null);
    }

    public void showAndWait() {
        alert.showAndWait();
    }

}
