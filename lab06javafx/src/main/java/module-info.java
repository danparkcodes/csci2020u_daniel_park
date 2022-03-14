module com.example.lab06javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab06javafx to javafx.fxml;
    exports com.example.lab06javafx;
}