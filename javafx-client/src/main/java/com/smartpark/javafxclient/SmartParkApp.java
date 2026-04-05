package com.smartpark.javafxclient;

import com.smartpark.javafxclient.ui.HomePage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SmartParkApp extends Application {

    @Override
    public void start(Stage stage) {
        HomePage homePage = new HomePage();

        Scene scene = new Scene(homePage.getView(), 1100, 650);
        stage.setTitle("SmartPark");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
