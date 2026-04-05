package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoginPage {

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

        Label loginLabel = new Label("Login");
        loginLabel.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Helvetica';"
        );

        sidebar.getChildren().addAll(appTitle, loginLabel);

        StackPane mainContent = new StackPane();

        Image image = new Image(getClass().getResource("/ramincar.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(930);
        imageView.setFitHeight(650);
        imageView.setPreserveRatio(false);
        imageView.setTranslateX(-20);

        VBox formBox = new VBox(18);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(35));
        formBox.setMaxWidth(380);
        formBox.setStyle(
                "-fx-background-color: rgba(255,255,255,0.88);" +
                        "-fx-background-radius: 18;"
        );

        Label title = new Label("Welcome Back");
        title.setStyle(
                "-fx-text-fill: #0B5E3C;" +
                        "-fx-font-size: 30px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;"
        );

        Label subtitle = new Label("Sign in to continue to SmartPark");
        subtitle.setStyle(
                "-fx-text-fill: #444444;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-family: 'Arial';"
        );

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(260);
        usernameField.setStyle(
                "-fx-background-radius: 10;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 10;"
        );

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(260);
        passwordField.setStyle(
                "-fx-background-radius: 10;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 10;"
        );

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(260);
        loginButton.setStyle(
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 12 24;" +
                        "-fx-cursor: hand;"
        );

        formBox.getChildren().addAll(
                title,
                subtitle,
                usernameField,
                passwordField,
                loginButton
        );

        mainContent.getChildren().addAll(imageView, formBox);

        root.setLeft(sidebar);
        root.setCenter(mainContent);

        return root;
    }
}