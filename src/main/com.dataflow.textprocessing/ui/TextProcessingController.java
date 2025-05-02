package com.dataflow.textprocessing.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TextProcessingController {

    @FXML private TextArea inputTextArea;
    @FXML private Label outputLabel;
    @FXML private ComboBox<String> actionSelector;
    @FXML private Button processButton;

    @FXML
    public void initialize() {
        actionSelector.getItems().addAll("Uppercase", "Word Count", "Sort Words");
        processButton.setOnAction(event -> processText());
    }

    private void processText() {
        String inputText = inputTextArea.getText();
        if (inputText.isEmpty()) {
            outputLabel.setText("Error: Please enter text to process.");
            return;
        }

        String selectedAction = actionSelector.getValue();
        String processedText;

        switch (selectedAction) {
            case "Uppercase":
                processedText = inputText.toUpperCase();
                break;
            case "Word Count":
                processedText = "Word Count: " + inputText.split("\\s+").length;
                break;
            case "Sort Words":
                processedText = String.join(" ", inputText.toLowerCase().split("\\s+"));
                break;
            default:
                processedText = "Select a valid transformation.";
        }

        outputLabel.setText("Processed Output: " + processedText);
    }
}
