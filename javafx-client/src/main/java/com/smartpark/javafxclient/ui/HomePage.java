package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HomePage {

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

        String sidebarLabelStyle =
                "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Helvetica';";

        dashboard.setStyle(sidebarLabelStyle);
        parking.setStyle(sidebarLabelStyle);
        about.setStyle(sidebarLabelStyle);
        help.setStyle(sidebarLabelStyle);

        sidebar.getChildren().addAll(appTitle, dashboard, parking, about, help);

        StackPane mainContent = new StackPane();

        Image image = new Image(getClass().getResource("/ramincar.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(930);
        imageView.setFitHeight(650);
        imageView.setPreserveRatio(false);
        imageView.setTranslateX(-20);

        VBox topText = new VBox(10);
        topText.setAlignment(Pos.TOP_CENTER);
        topText.setPadding(new Insets(25, 0, 0, 0));

        Label welcomeTitle = new Label("Welcome to SmartPark");
        welcomeTitle.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 42px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;"
        );

        Label subtitle = new Label("Find the best parking available");
        subtitle.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-font-weight: bold;"
        );

        topText.getChildren().addAll(welcomeTitle, subtitle);

        HBox buttonRow = new HBox(20);
        buttonRow.setAlignment(Pos.BOTTOM_CENTER);
        buttonRow.setPadding(new Insets(0, 0, 25, 0));
        buttonRow.setTranslateY(30);

        Button studentButton = new Button("Student");
        Button facultyButton = new Button("Faculty");
        Button visitorButton = new Button("Visitor");

        String buttonStyle =
                "-fx-background-color: rgba(255,255,255,0.95);" +
                        "-fx-text-fill: #1f1f1f;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 12 24;" +
                        "-fx-cursor: hand;";

        studentButton.setStyle(buttonStyle);
        facultyButton.setStyle(buttonStyle);
        visitorButton.setStyle(buttonStyle);

        studentButton.setPrefWidth(170);
        facultyButton.setPrefWidth(170);
        visitorButton.setPrefWidth(170);

        buttonRow.getChildren().addAll(studentButton, facultyButton, visitorButton);

        BorderPane overlayLayout = new BorderPane();
        overlayLayout.setPadding(new Insets(20, 35, 30, 35));
        overlayLayout.setTop(topText);
        overlayLayout.setBottom(buttonRow);

        BorderPane.setAlignment(topText, Pos.TOP_CENTER);
        BorderPane.setAlignment(buttonRow, Pos.BOTTOM_CENTER);

        mainContent.getChildren().addAll(imageView, overlayLayout);

        root.setLeft(sidebar);
        root.setCenter(mainContent);

        return root;
    }
}