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
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Login page for SmartPark.
 * Sends login requests to the backend and shows a temporary success screen
 * until full role dashboards are connected.
 */
public class LoginPage {

    /**
     * Builds and returns the login page UI.
     *
     * @param stage the primary stage, used for scene switching after login
     * @return the root layout for the login page
     */
    public StackPane getView(Stage stage) {
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

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(250);

        Button loginButton = new Button("Login");
        String buttonStyle =
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10 20;";

        loginButton.setStyle(buttonStyle);
        loginButton.setPrefWidth(200);

        Label messageLabel = new Label();
        messageLabel.setStyle(
                "-fx-text-fill: #cc0000;" +
                        "-fx-font-size: 12px;"
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

                String returnedUsername = extractJsonValue(response, "username");
                String returnedRole = extractJsonValue(response, "role");

                UserSession.setUsername(returnedUsername);
                UserSession.setRole(returnedRole);

                routeToDashboard(stage, returnedUsername, returnedRole);

            } catch (Exception ex) {
                String error = ex.getMessage();

                if (error != null && error.contains("\"message\":\"")) {
                    int start = error.indexOf("\"message\":\"") + 11;
                    int end = error.indexOf("\"", start);

                    if (end > start) {
                        messageLabel.setText(error.substring(start, end));
                        return;
                    }
                }

                messageLabel.setText("Login failed. Please try again.");
            }
        });



        loginBox.getChildren().addAll(
                title,
                usernameField,
                passwordField,
                loginButton,
                messageLabel
        );

        root.getChildren().addAll(background, loginBox);
        return root;
    }

    /**
     * Routes the user to the appropriate dashboard based on their role.
     *
     * This method is called after a successful login. It determines which
     * dashboard to display (Admin, Faculty, or Student) using the role
     * returned from the backend authentication response.
     *
     * @param stage    the primary JavaFX stage used to switch scenes
     * @param username the authenticated user's username (for future use/display)
     * @param role     the user's role (ADMIN, FACULTY, or STUDENT)
     *
     * @throws RuntimeException if the role is not recognized
     */
    private void routeToDashboard(Stage stage, String username, String role) {
        Scene dashboardScene;

        switch (role.toUpperCase()) {
            case "ADMIN":
                dashboardScene = new Scene(new AdminPage().getView(stage), 1100, 650);
                break;
            case "FACULTY":
                dashboardScene = new Scene(new FacultyDashboardPage().getView(stage), 1100, 650);
                break;
            case "STUDENT":
                dashboardScene = new Scene(new StudentDashboardPage().getView(stage), 1100, 650);
                break;
            default:
                throw new RuntimeException("Unknown role: " + role);
        }

        stage.setScene(dashboardScene);
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
            os.write(jsonBody.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }

        int responseCode = conn.getResponseCode();

        InputStream inputStream =
                (responseCode == 200) ? conn.getInputStream() : conn.getErrorStream();

        String response;
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            response = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        }

        if (responseCode != 200) {
            throw new Exception("Login failed: " + response);
        }

        return response;
    }

    /**
     * Very simple JSON value extractor.
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
}