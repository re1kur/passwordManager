module passwordManager {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;

    opens org.example;
    opens org.example.controllers to javafx.fxml;
}