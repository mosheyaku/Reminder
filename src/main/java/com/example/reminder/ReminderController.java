package com.example.reminder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.HashMap;

/**
 * The ReminderController class controls the behavior of the Reminder application's user interface.
 * It is responsible for handling user interactions and managing data storage.
 */
public class ReminderController {

    @FXML
    private ComboBox<String> dayComboBox;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private TextArea textArea;

    @FXML
    private ComboBox<String> yearComboBox;

    @FXML
    private VBox vbox;

    private enum Months {Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec}

    private HashMap<Date, String> hashmap;
    private final int SHORT_MONTH = 28;
    private final int MEDIUM_MONTH = 30;
    private final int LONG_MONTH = 31;
    private final int FIRST_YEAR = 2020;
    private final int LAST_YEAR = 2025;
    private boolean hasClosingEvent = false;

    /**
     * Initializes the ReminderController after its root element has been processed.
     * It sets up the combo boxes, reads data from a file, and prepares for the closing event.
     */
    public void initialize() {
        hashmap = new HashMap<Date, String>();
        setComboBoxes();
        readFromFile();
    }

    /**
     * Retrieves the current date selected in the combo boxes.
     *
     * @return The current date.
     */
    private Date getCurrentDate() {
        return new Date(Integer.parseInt(dayComboBox.getValue()),
                monthComboBox.getValue(),
                Integer.parseInt(yearComboBox.getValue()));
    }

    @FXML
    void loadPressed(ActionEvent event) {
        String text = hashmap.get(getCurrentDate());
        textArea.setText(text);
    }

    @FXML
    void savePressed(ActionEvent event) {
        String text = textArea.getText();
        hashmap.put(getCurrentDate(), text);
        if (!hasClosingEvent) {
            hasClosingEvent = true;
            addClosingEvent();
        }
    }

    @FXML
    void monthPressed(ActionEvent event) {
        String currentMonth = monthComboBox.getValue();
        dayComboBox.setItems(setDaysOfMonths(currentMonth).getItems());
        dayComboBox.setValue("1");
        textArea.setText("");
    }

    @FXML
    void dayPressed(ActionEvent event) {
        textArea.setText("");
    }

    @FXML
    void yearPressed(ActionEvent event) {
        textArea.setText("");
    }

    /**
     * Sets up the combo boxes for day, month, and year.
     */
    private void setComboBoxes() {
        for (int i = 1; i <= LONG_MONTH; i++)
            dayComboBox.getItems().add(i + "");
        dayComboBox.setValue("1");

        for (Months month : Months.values())
            monthComboBox.getItems().add("" + month);
        monthComboBox.setValue("Jan");

        for (int i = FIRST_YEAR; i <= LAST_YEAR; i++)
            yearComboBox.getItems().add(i + "");
        yearComboBox.setValue(FIRST_YEAR + "");
    }

    /**
     * Sets the number of days in a month for the given month.
     *
     * @param month The month for which to set the number of days.
     * @return A ComboBox containing the days of the month.
     */
    private ComboBox setDaysOfMonths(String month) {
        ComboBox days = new ComboBox<>();
        int daysInMonth;
        switch (month) {
            case "Apr":
            case "Jun":
            case "Sep":
            case "Nov":
                daysInMonth = MEDIUM_MONTH;
                break;
            case "Feb":
                daysInMonth = SHORT_MONTH;
                break;
            default:
                daysInMonth = LONG_MONTH;
                break;
        }
        for (int i = 1; i <= daysInMonth; i++)
            days.getItems().add(i + "");
        return days;
    }

    /**
     * Opens a file chooser dialog and returns the selected file.
     *
     * @return The selected file or null if no file is selected.
     */
    private File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Please select file:");
        fileChooser.setInitialDirectory(new File("."));
        return fileChooser.showOpenDialog(null);
    }

    /**
     * Reads the data from a file and populates the hashmap with the stored data.
     */
    private void readFromFile() {
        File file = getFile();
        if (file != null) {
            try {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fi);
                hashmap = (HashMap<Date, String>) ois.readObject();
                ois.close();
                fi.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Writes the hashmap data to a file.
     */
    private void writeToFile() {
        File file = getFile();
        if (file != null) {
            try {
                FileOutputStream fo = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fo);
                out.writeObject(hashmap);
                out.close();
                fo.close();
            } catch (Exception e) {
                System.out.println("Error 2");
            }
        }
    }

    /**
     * Adds a closing event handler to the application's primary stage.
     * When the window is closed, the data is written to a file.
     */
    private void addClosingEvent() {
        Stage stage = (Stage) ((Node) vbox).getScene().getWindow();
        stage.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event1 -> {
            writeToFile();
        });
    }
}
