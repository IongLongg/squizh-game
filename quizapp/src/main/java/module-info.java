module com.example.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.quizapp to javafx.fxml;
    exports com.example.quizapp;
    exports com.example.quizapp.controller;
    opens com.example.quizapp.controller to javafx.fxml;
}