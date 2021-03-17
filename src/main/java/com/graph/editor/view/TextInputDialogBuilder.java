package com.graph.editor.view;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class TextInputDialogBuilder {

    private static final String DIALOG_TITLE = "Identifier";
    private static final String DIALOG_CONTENT_TEXT = "Please, enter identifier";

    private final TextInputDialog textInputDialog;

    public TextInputDialogBuilder() {
        textInputDialog = new TextInputDialog();
        setInformationDialogParams();
    }

    private void setInformationDialogParams() {
        textInputDialog.setContentText(DIALOG_CONTENT_TEXT);
        textInputDialog.setTitle(DIALOG_TITLE);
        textInputDialog.setHeaderText(null);
    }

    public String getIdentifier() {
        Optional<String> result = textInputDialog.showAndWait();
        return result.orElse("");
    }
}