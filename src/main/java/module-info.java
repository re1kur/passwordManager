module passwordManager {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.controllers to javafx.fxml;
    opens org.example.handlers;
    opens org.example.classes;
}