module com.example.lab05 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.lab05 to javafx.fxml;
    exports com.example.lab05;
}