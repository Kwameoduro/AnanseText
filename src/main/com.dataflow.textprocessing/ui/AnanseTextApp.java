package com.dataflow.textprocessing.ui;

import com.dataflow.textprocessing.files.FileReaderUtil;
import com.dataflow.textprocessing.files.FileWriterUtil;
import com.dataflow.textprocessing.utils.ExceptionHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AnanseTextApp extends Application {

    private File currentFile = null;  // Keep track of the loaded file

    @Override
    public void start(Stage primaryStage) {
        // Main Layout Setup
        TabPane tabPane = new TabPane();

        // Tab 1: Text Transformation Tab
        Tab textTransformationTab = new Tab("Text Transformation");
        textTransformationTab.setClosable(false);
        textTransformationTab.setContent(createTextTransformationScene(primaryStage));

        // Tab 2: Text Cleanup Tab
        Tab textCleanupTab = new Tab("Text Cleanup");
        textCleanupTab.setClosable(false);
        textCleanupTab.setContent(createTextCleanupScene(primaryStage));

        // Tab 3: Word Manipulation Tab
        Tab wordManipulationTab = new Tab("Word Manipulation");
        wordManipulationTab.setClosable(false);
        wordManipulationTab.setContent(createWordManipulationScene(primaryStage));

        // Tab 4: Text Analysis Tab
        Tab textAnalysisTab = new Tab("Text Analysis");
        textAnalysisTab.setClosable(false);
        textAnalysisTab.setContent(createTextAnalysisScene(primaryStage));

        // Tab 5: File Operations Tab
        Tab fileOperationsTab = new Tab("File Operations");
        fileOperationsTab.setClosable(false);
        fileOperationsTab.setContent(createFileOperationsScene(primaryStage));

        // Tab 6: Data Management Tab
        Tab dataManagementTab = new Tab("Data Management");
        dataManagementTab.setClosable(false);
        dataManagementTab.setContent(createDataManagementScene());
        tabPane.getTabs().add(dataManagementTab);



        // Add 5 tabs to the TabPane( get.tabs() is already implemented in data management)
        tabPane.getTabs().addAll(textTransformationTab, textCleanupTab, wordManipulationTab, textAnalysisTab, fileOperationsTab);

        // Main Scene with TabPane
        VBox mainLayout = new VBox(tabPane);
        Scene mainScene = new Scene(mainLayout, 900, 400);

        primaryStage.setTitle("AnanseText - Text Processing Tool");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    private VBox createTextTransformationScene(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextArea inputTextArea = new TextArea();
        inputTextArea.setPromptText("Enter text here...");
        styleTextArea(inputTextArea);

        TextArea outputTextArea = new TextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setPromptText("Processed output will appear here...");
        styleTextArea(outputTextArea);

        ComboBox<String> transformationActions = new ComboBox<>();
        transformationActions.getItems().addAll(
                "Uppercase", "Title Case", "Camel Case", "Capitalize Words");
        transformationActions.setPromptText("Select action");

        Button processButton = createStyledButton("Process Text");
        processButton.setOnAction(event -> {
            String inputText = inputTextArea.getText();
            if (inputText.isEmpty()) {
                showAlert("Error", "Please enter text to process.");
                return;
            }

            String selectedAction = transformationActions.getValue();
            if (selectedAction == null || selectedAction.isEmpty()) {
                showAlert("Error", "Please select an action.");
                return;
            }

            try {
                String processedText = processText(inputText, selectedAction);
                outputTextArea.setText(processedText);
            } catch (Exception e) {
                ExceptionHandler.handleException("Text Processing", e);
            }
        });

        layout.getChildren().addAll(inputTextArea, transformationActions, processButton, outputTextArea);
        return layout;
    }

    private VBox createTextCleanupScene(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextArea inputTextArea = new TextArea();
        inputTextArea.setPromptText("Enter text here...");
        styleTextArea(inputTextArea);

        TextArea outputTextArea = new TextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setPromptText("Processed output will appear here...");
        styleTextArea(outputTextArea);

        ComboBox<String> cleanupActions = new ComboBox<>();
        cleanupActions.getItems().addAll(
                "Remove Special Characters", "Remove Punctuation", "Remove Duplicates");
        cleanupActions.setPromptText("Select action");

        Button processButton = createStyledButton("Process Text");
        processButton.setOnAction(event -> {
            String inputText = inputTextArea.getText();
            if (inputText.isEmpty()) {
                showAlert("Error", "Please enter text to process.");
                return;
            }

            String selectedAction = cleanupActions.getValue();
            if (selectedAction == null || selectedAction.isEmpty()) {
                showAlert("Error", "Please select an action.");
                return;
            }

            try {
                String processedText = processText(inputText, selectedAction);
                outputTextArea.setText(processedText);
            } catch (Exception e) {
                ExceptionHandler.handleException("Text Processing", e);
            }
        });

        layout.getChildren().addAll(inputTextArea, cleanupActions, processButton, outputTextArea);
        return layout;
    }

    private VBox createWordManipulationScene(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextArea inputTextArea = new TextArea();
        inputTextArea.setPromptText("Enter text here...");
        styleTextArea(inputTextArea);

        TextArea outputTextArea = new TextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setPromptText("Processed output will appear here...");
        styleTextArea(outputTextArea);

        ComboBox<String> wordActions = new ComboBox<>();
        wordActions.getItems().addAll(
                "Generate Acronym", "Shuffle Words", "Sort Words Alphabetically", "Replace Word");
        wordActions.setPromptText("Select action");

        TextField wordToReplaceField = new TextField();
        wordToReplaceField.setPromptText("Word to replace");
        styleTextField(wordToReplaceField);

        TextField replacementWordField = new TextField();
        replacementWordField.setPromptText("Replacement word");
        styleTextField(replacementWordField);

        Button processButton = createStyledButton("Process Text");
        processButton.setOnAction(event -> {
            String inputText = inputTextArea.getText();
            if (inputText.isEmpty()) {
                showAlert("Error", "Please enter text to process.");
                return;
            }

            String selectedAction = wordActions.getValue();
            if (selectedAction == null || selectedAction.isEmpty()) {
                showAlert("Error", "Please select an action.");
                return;
            }

            try {
                String processedText = processText(inputText, selectedAction, wordToReplaceField.getText(), replacementWordField.getText());
                outputTextArea.setText(processedText);
            } catch (Exception e) {
                ExceptionHandler.handleException("Text Processing", e);
            }
        });

        layout.getChildren().addAll(inputTextArea, wordActions, wordToReplaceField, replacementWordField, processButton, outputTextArea);
        return layout;
    }

    private VBox createTextAnalysisScene(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextArea inputTextArea = new TextArea();
        inputTextArea.setPromptText("Enter text here...");
        styleTextArea(inputTextArea);

        TextArea outputTextArea = new TextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setPromptText("Processed output will appear here...");
        styleTextArea(outputTextArea);

        ComboBox<String> analysisActions = new ComboBox<>();
        analysisActions.getItems().addAll(
                "Count Unique Words", "Word Frequency", "Count Occurrences", "Find Most Frequent Word", "Find Longest Word");
        analysisActions.setPromptText("Select action");

        Button processButton = createStyledButton("Process Text");
        processButton.setOnAction(event -> {
            String inputText = inputTextArea.getText();
            if (inputText.isEmpty()) {
                showAlert("Error", "Please enter text to process.");
                return;
            }

            String selectedAction = analysisActions.getValue();
            if (selectedAction == null || selectedAction.isEmpty()) {
                showAlert("Error", "Please select an action.");
                return;
            }

            try {
                String processedText = processText(inputText, selectedAction);
                outputTextArea.setText(processedText);
            } catch (Exception e) {
                ExceptionHandler.handleException("Text Processing", e);
            }
        });

        layout.getChildren().addAll(inputTextArea, analysisActions, processButton, outputTextArea);
        return layout;
    }

    private VBox createFileOperationsScene(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextArea fileTextArea = new TextArea();
        fileTextArea.setEditable(true);
        fileTextArea.setPromptText("Load a file to edit.");
        styleTextArea(fileTextArea);

        Button loadFileButton = createStyledButton("Load File");
        Button saveOutputButton = createStyledButton("Save Output");

        loadFileButton.setOnAction(e -> loadFileAction(primaryStage, fileTextArea));
        saveOutputButton.setOnAction(e -> saveOutputAction(primaryStage, fileTextArea));

        layout.getChildren().addAll(loadFileButton, saveOutputButton, fileTextArea);
        return layout;
    }

    private VBox createDataManagementScene() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField idField = new TextField();
        idField.setPromptText("Entry ID");
        styleTextField(idField);

        TextField contentField = new TextField();
        contentField.setPromptText("Entry Content");
        styleTextField(contentField);

        ListView<TextEntry> entryListView = new ListView<>();
        Set<TextEntry> entrySet = new HashSet<>();
        List<TextEntry> entryList = new ArrayList<>();

        Button addButton = createStyledButton("Add");
        Button updateButton = createStyledButton("Update");
        Button deleteButton = createStyledButton("Delete");

        addButton.setOnAction(e -> {
            String id = idField.getText().trim();
            String content = contentField.getText().trim();
            if (id.isEmpty() || content.isEmpty()) {
                showAlert("Validation Error", "Both ID and content are required.");
                return;
            }
            TextEntry newEntry = new TextEntry(id, content);
            if (entrySet.contains(newEntry)) {
                showAlert("Duplicate", "Entry with this ID already exists.");
            } else {
                entrySet.add(newEntry);
                entryList.add(newEntry);
                entryListView.getItems().setAll(entryList);
                idField.clear();
                contentField.clear();
            }
        });

        updateButton.setOnAction(e -> {
            String id = idField.getText().trim();
            String content = contentField.getText().trim();
            if (id.isEmpty() || content.isEmpty()) {
                showAlert("Validation Error", "Both ID and content are required.");
                return;
            }
            TextEntry updatedEntry = new TextEntry(id, content);
            for (TextEntry entry : entryList) {
                if (entry.equals(updatedEntry)) {
                    entry.setContent(content);
                    entryListView.getItems().setAll(entryList);
                    showAlertSuccess("Updated", "Entry updated successfully.");
                    return;
                }
            }
            showAlertSuccess("Not Found", "No entry with this ID found.");
        });

        deleteButton.setOnAction(e -> {
            String id = idField.getText().trim();
            if (id.isEmpty()) {
                showAlert("Validation Error", "ID is required to delete.");
                return;
            }
            TextEntry toDelete = new TextEntry(id, "");
            if (entrySet.remove(toDelete)) {
                entryList.removeIf(entry -> entry.equals(toDelete));
                entryListView.getItems().setAll(entryList);
                showAlert("Deleted", "Entry deleted successfully.");
            } else {
                showAlert("Not Found", "No entry with this ID found.");
            }
        });

        layout.getChildren().addAll(
                new Label("Manage Text Entries:"),
                idField, contentField,
                new HBox(10, addButton, updateButton, deleteButton),
                entryListView
        );
        return layout;
    }


    private void loadFileAction(Stage primaryStage, TextArea fileTextArea) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setTitle("Open Text File");
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                currentFile = file;  // Track the current file
                String content = FileReaderUtil.readFileAsString(file.getAbsolutePath());
                fileTextArea.setText(content);
            } catch (IOException ex) {
                ExceptionHandler.handleException("Loading File", ex);
            }
        }
    }

    private void saveOutputAction(Stage primaryStage, TextArea fileTextArea) {
        if (currentFile == null) {
            showAlert("Error", "No file loaded to save.");
            return;
        }

        // Use FileChooser for saving the file and allowing renaming
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setTitle("Save File");

        // Set the initial directory to the current file's directory
        if (currentFile != null) {
            fileChooser.setInitialDirectory(currentFile.getParentFile());  // Set the directory to the current file's location
            fileChooser.setInitialFileName(currentFile.getName());  // Set the file name to the current file name (so it can be renamed)
        }

        // Show the file save dialog and get the selected file
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            try {
                String content = fileTextArea.getText();
                if (!content.isEmpty()) {
                    // Save the content to the selected file
                    FileWriterUtil.writeToFile(file.getAbsolutePath(), content);
                    showAlertSuccess("Saved", "File saved successfully.");
                    currentFile = file;  // Update the current file after saving
                } else {
                    showAlert("Error", "The file content is empty. Nothing to save.");
                }
            } catch (IOException ex) {
                ExceptionHandler.handleException("Saving File", ex);
            }
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlertSuccess(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

// REPLACE YOUR EXISTING processText METHODS WITH THESE:

    private String processText(String text, String action) {
        switch (action) {
            case "Uppercase":
                return text.toUpperCase();
            case "Title Case":
                return Arrays.stream(text.split("\\s+"))
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "));
            case "Camel Case":
                return Arrays.stream(text.split("\\s+"))
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                        .collect(Collectors.joining(""));
            case "Capitalize Words":
                return Arrays.stream(text.split("\\s+"))
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                        .collect(Collectors.joining(" "));
            case "Remove Special Characters":
                return text.replaceAll("[^a-zA-Z0-9\\s]", "");
            case "Remove Punctuation":
                return text.replaceAll("\\p{Punct}", "");
            case "Remove Duplicates":
                return Arrays.stream(text.split("\\s+"))
                        .distinct()
                        .collect(Collectors.joining(" "));
            case "Generate Acronym":
                return Arrays.stream(text.split("\\s+"))
                        .filter(word -> !word.isEmpty())
                        .map(word -> word.substring(0, 1).toUpperCase())
                        .collect(Collectors.joining());
            case "Shuffle Words":
                List<String> words = new ArrayList<>(Arrays.asList(text.split("\\s+")));
                Collections.shuffle(words);
                return String.join(" ", words);
            case "Sort Words Alphabetically":
                return Arrays.stream(text.split("\\s+"))
                        .sorted(String.CASE_INSENSITIVE_ORDER)
                        .collect(Collectors.joining(" "));
            case "Count Unique Words":
                long count = Arrays.stream(text.toLowerCase().split("\\s+"))
                        .distinct()
                        .count();
                return "Unique word count: " + count;
            case "Word Frequency":
                Map<String, Long> freqMap = Arrays.stream(text.toLowerCase().split("\\s+"))
                        .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
                return freqMap.entrySet().stream()
                        .map(e -> e.getKey() + ": " + e.getValue())
                        .collect(Collectors.joining("\n"));
            case "Count Occurrences":
                Map<String, Long> countMap = Arrays.stream(text.toLowerCase().split("\\s+"))
                        .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
                return countMap.entrySet().stream()
                        .map(e -> e.getKey() + ": " + e.getValue())
                        .collect(Collectors.joining("\n"));
            case "Find Most Frequent Word":
                Map<String, Long> freq = Arrays.stream(text.toLowerCase().split("\\s+"))
                        .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
                return freq.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(e -> "Most frequent word: " + e.getKey() + " (" + e.getValue() + " times)")
                        .orElse("No words found.");
            case "Find Longest Word":
                return Arrays.stream(text.split("\\s+"))
                        .max(Comparator.comparingInt(String::length))
                        .map(word -> "Longest word: " + word)
                        .orElse("No words found.");
            default:
                return text;
        }
    }

    private String processText(String text, String action, String wordToReplace, String replacementWord) {
        switch (action) {
            case "Replace Word":
                return text.replaceAll("\\b" + Pattern.quote(wordToReplace) + "\\b", replacementWord);
            default:
                return processText(text, action);
        }
    }


    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px;");
        button.setEffect(new DropShadow(10, 5, 5, javafx.scene.paint.Color.GRAY));
        button.setOnMouseEntered((MouseEvent e) -> button.setStyle("-fx-background-color: #005fa3; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px;"));
        button.setOnMouseExited((MouseEvent e) -> button.setStyle("-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px;"));
        return button;
    }

    private void styleTextArea(TextArea textArea) {
        textArea.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #cccccc; -fx-border-radius: 5px; -fx-font-size: 14px;");
        textArea.setEffect(new DropShadow(10, 5, 5, javafx.scene.paint.Color.GRAY));
    }

    private void styleTextField(TextField textField) {
        textField.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #cccccc; -fx-border-radius: 5px; -fx-font-size: 14px;");
    }
    public static class TextEntry {
        private String id;
        private String content;

        public TextEntry(String id, String content) {
            this.id = id;
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            TextEntry that = (TextEntry) obj;
            return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return id + ": " + content;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
