module com.smartpark.javafxclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.smartpark.javafxclient to javafx.fxml;
    exports com.smartpark.javafxclient;
    exports ui;
    opens ui to javafx.fxml;
    exports com.smartpark.javafxclient.ui;
    opens com.smartpark.javafxclient.ui to javafx.fxml;
}