package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoginPage {

    public StackPane getView() {

        StackPane root = new StackPane();

        Image image = new Image(getClass().getResource("/ramincar.png").toExternalForm());
        ImageView background = new ImageView(image);
        background.setFitWidth(1100);
        background.setFitHeight(650);
        background.setPreserveRatio(false);

        VBox loginBox = new VBox(15);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(30));
        loginBox.setMaxWidth(350);
        loginBox.setStyle(
                "-fx-background-color: rgba(255,255,255,0.9);" +
                        "-fx-background-radius: 15;"
        );

        Label title = new Label("Login");
        title.setStyle(
                "-fx-font-size: 26px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #0B5E3C;"
        );

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setMaxWidth(250);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setMaxWidth(250);

        Button studentLogin = new Button("Login as Student");
        Button adminLogin = new Button("Login as Admin");

        String buttonStyle =
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10 20;";

        studentLogin.setStyle(buttonStyle);
        adminLogin.setStyle(buttonStyle);

        studentLogin.setPrefWidth(200);
        adminLogin.setPrefWidth(200);

        Label errorMessage = new Label("Login error message would go here");
        errorMessage.setStyle(
                "-fx-text-fill: #cc0000;" +
                        "-fx-font-size: 12px;"
        );

        loginBox.getChildren().addAll(
                title,
                username,
                password,
                studentLogin,
                adminLogin,
                errorMessage
        );

        root.getChildren().addAll(background, loginBox);

        return root;
    }
}