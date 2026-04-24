package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ViewParkingPages {

    public BorderPane getView() {

        BorderPane root = new BorderPane();

        VBox sidebar = new VBox(25);
        sidebar.setPadding(new Insets(25));
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #0B5E3C;");

        Label appTitle = new Label("SmartPark");
        appTitle.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 22px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;"
        );

        Label dashboard = new Label("Dashboard");
        Label parking = new Label("Parking");
        Label about = new Label("About");
        Label help = new Label("Help");
        Label logout = new Label("Logout");

        String sidebarStyle =
                "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Helvetica';";

        dashboard.setStyle(sidebarStyle);
        parking.setStyle(sidebarStyle);
        about.setStyle(sidebarStyle);
        help.setStyle(sidebarStyle);
        logout.setStyle(sidebarStyle);

        sidebar.getChildren().addAll(appTitle, dashboard, parking, about, help, logout);

        VBox content = new VBox(15);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(40));

        Label title = new Label("Parking Lots");
        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;"
        );

        Label lot1 = new Label("Lot A - 25 spaces");
        Label lot2 = new Label("Lot B - 10 spaces");
        Label lot3 = new Label("Lot C - Full");

        lot1.setStyle("-fx-font-size: 16px;");
        lot2.setStyle("-fx-font-size: 16px;");
        lot3.setStyle("-fx-font-size: 16px;");

        content.getChildren().addAll(title, lot1, lot2, lot3);

        root.setLeft(sidebar);
        root.setCenter(content);

        return root;
    }
}