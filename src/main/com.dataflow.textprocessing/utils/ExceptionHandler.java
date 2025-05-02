package com.dataflow.textprocessing.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHandler {

    /**
     * Handles an exception by logging it and displaying an error dialog.
     *
     * @param context  A short description of where the exception occurred.
     * @param ex       The exception to handle.
     */
    public static void handleException(String context, Exception ex) {
        logException(context, ex);
        showErrorDialog(context, ex);
    }

    /**
     * Logs the exception details to the console.
     *
     * @param context  A short description of the context where the exception occurred.
     * @param ex       The exception to log.
     */
    private static void logException(String context, Exception ex) {
        System.err.println("Exception in " + context + ": " + ex.getMessage());
        ex.printStackTrace();
    }

    /**
     * Displays a JavaFX error dialog with exception information.
     *
     * @param context  A short context description.
     * @param ex       The exception to display.
     */
    private static void showErrorDialog(String context, Exception ex) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred in: " + context);
        alert.setContentText(ex.getMessage());

        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String exceptionText = sw.toString();

        // Optional: display full stack trace in a detailed expandable dialog (if needed)
        // TextArea textArea = new TextArea(exceptionText);
        // textArea.setEditable(false);
        // alert.getDialogPane().setExpandableContent(textArea);

        alert.showAndWait();
    }
}
