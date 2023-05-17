package com.example.reminder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.HashMap;

public class ReminderController {

    @FXML
    private ComboBox<String> dayComboBox;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private TextArea textArea;

    @FXML
    private ComboBox<String> yearComboBox;


    private enum Months {Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec}


    private HashMap<Date, String> hashmap;
    private final int SHORT_MONTH = 28;

    private final int MEDIUM_MONTH = 30;

    private final int LONG_MONTH = 31;

   // private final int MONTH = 12;

    private final int FIRST_YEAR = 2020;

    private final int LAST_YEAR = 2025;

    public void initialize(){
        hashmap = new HashMap<Date, String>();
        setComboBoxes();
    }

    @FXML
    void loadPressed(ActionEvent event) {

    }

    @FXML
    void savePressed(ActionEvent event) {

    }

    @FXML
    void monthPressed(ActionEvent event) {
        String currentMonth = monthComboBox.getValue();
        dayComboBox.setItems(setDaysOfMonths(currentMonth).getItems());
        dayComboBox.setValue("1");
    }


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

}
