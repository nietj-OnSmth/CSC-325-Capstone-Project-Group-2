module com.smartpark.javafxclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.smartpark.javafxclient to javafx.fxml;
    exports com.smartpark.javafxclient;
}