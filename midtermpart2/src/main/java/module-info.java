module com.example.midtermpart2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.midtermpart2 to javafx.fxml;
    exports com.example.midtermpart2;
}