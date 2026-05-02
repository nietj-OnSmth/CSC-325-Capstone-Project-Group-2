module com.smartpark.javafxclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    opens com.smartpark.javafxclient.dto to com.fasterxml.jackson.databind;

    exports com.smartpark.javafxclient;
}
