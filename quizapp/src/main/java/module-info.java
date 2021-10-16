module com.example.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires com.google.gson;


    opens com.company to javafx.fxml;
    exports com.company;
}