module com.example.reminder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.reminder to javafx.fxml;
    exports com.example.reminder;
}