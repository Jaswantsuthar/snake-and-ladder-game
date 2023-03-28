module com.example.sgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sgame to javafx.fxml;
    exports com.example.sgame;
}