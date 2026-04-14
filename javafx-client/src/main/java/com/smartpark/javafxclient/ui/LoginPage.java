package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LoginPage {

    public StackPane getView() {
    public BorderPane getView(Stage stage) {
        BorderPane root = new BorderPane();

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
        Label messageLabel = new Label();
        messageLabel.setStyle(
                "-fx-text-fill: #B00020;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-family: 'Arial';"
        );

        formBox.getChildren().addAll(
                title,
                subtitle,
                usernameField,
                passwordField,
                loginButton,
                messageLabel
        );

        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Please enter both username and password.");
                return;
            }

            try {
                String response = sendLoginRequest(username, password);

                // super simple parsing for current backend response:
                // {"username":"student1","role":"STUDENT"}
                String returnedUsername = extractJsonValue(response, "username");
                String returnedRole = extractJsonValue(response, "role");

                showSuccessScreen(stage, returnedUsername, returnedRole);

            } catch (Exception ex) {

                String error = ex.getMessage();

                // Extract just the message field from backend JSON
                if (error.contains("\"message\":\"")) {
                    int start = error.indexOf("\"message\":\"") + 11;
                    int end = error.indexOf("\"", start);

                    if (end > start) {
                        messageLabel.setText(error.substring(start, end));
                        return;
                    }
                }

                // fallback
                messageLabel.setText("Login failed. Please try again.");
            }
        });

        mainContent.getChildren().addAll(imageView, formBox);

        root.setLeft(sidebar);
        root.setCenter(mainContent);

        return root;
    }

    /**
     * Sends login request to backend and returns response body as a string.
     */
    private String sendLoginRequest(String username, String password) throws Exception {
        URL url = new URL("http://localhost:8080/api/auth/login");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonBody = "{"
                + "\"username\":\"" + username + "\","
                + "\"password\":\"" + password + "\""
                + "}";

        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonBody.getBytes());
            os.flush();
        }

        int responseCode = conn.getResponseCode();

        InputStream inputStream;
        if (responseCode == 200) {
            inputStream = conn.getInputStream();
        } else {
            inputStream = conn.getErrorStream();
        }

        String response;
        try (Scanner scanner = new Scanner(inputStream)) {
            response = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        }

        if (responseCode != 200) {
            throw new Exception("Login failed: " + response);
        }

        return response;
    }

    /**
     * Very simple JSON value extractor
     * Assumes flat JSON like: {"username":"student1","role":"STUDENT"}
     */
    private String extractJsonValue(String json, String key) {
        String searchKey = "\"" + key + "\":\"";
        int start = json.indexOf(searchKey);

        if (start == -1) {
            return "";
        }

        start += searchKey.length();
        int end = json.indexOf("\"", start);

        if (end == -1) {
            return "";
        }

        return json.substring(start, end);
    }

    /**
     * Temporary success screen until full dashboards are built.
     */
    private void showSuccessScreen(Stage stage, String username, String role) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: white;");

        Label successTitle = new Label("Login Successful");
        successTitle.setStyle(
                "-fx-text-fill: #0B5E3C;" +
                        "-fx-font-size: 28px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;"
        );

        Label userLabel = new Label("Hi " + username + ", your role is " + role);
        userLabel.setStyle(
                "-fx-text-fill: #333333;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-family: 'Arial';"
        );

        Button backButton = new Button("Back to Login");
        backButton.setStyle(
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10 20;" +
                        "-fx-cursor: hand;"
        );

        backButton.setOnAction(e -> {
            BorderPane loginView = getView(stage);
            Scene loginScene = new Scene(loginView, 1100, 650);
            stage.setScene(loginScene);
        });

        layout.getChildren().addAll(successTitle, userLabel, backButton);

        Scene successScene = new Scene(layout, 1100, 650);
        stage.setScene(successScene);
    }
}