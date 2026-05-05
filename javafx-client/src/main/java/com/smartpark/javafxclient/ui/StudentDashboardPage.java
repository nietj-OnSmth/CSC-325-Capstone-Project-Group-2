package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * StudentDashboardPage represents the main interface for student users.
 *
 * This dashboard allows students to:
 * - View their role and access level
 * - Request parking recommendations from the backend
 * - Navigate to related pages such as About, Help, and Logout
 *
 * This class is part of the JavaFX frontend and communicates with the
 * Spring Boot backend via HTTP requests.
 */
public class StudentDashboardPage {

    /**
     * Builds and returns the Student Dashboard UI.
     *
     * This method creates the sidebar navigation and main dashboard content.
     * It also attaches event handlers to buttons, including both the sidebar
     * Parking button and the main Find Parking button.
     *
     * @param stage the primary JavaFX stage used for scene navigation
     * @return the root layout for the student dashboard
     */
    public BorderPane getView(Stage stage) {

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

        String sidebarButtonStyle =
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-alignment: CENTER-LEFT;" +
                        "-fx-padding: 8 12;" +
                        "-fx-cursor: hand;" +
                        "-fx-border-color: transparent;";

        Button dashboardButton = new Button("Dashboard");
        Button parkingButton = new Button("Parking");
        Button aboutButton = new Button("About");
        Button helpButton = new Button("Help");
        Button logoutButton = new Button("Logout");

        dashboardButton.setStyle(sidebarButtonStyle);
        parkingButton.setStyle(sidebarButtonStyle);
        aboutButton.setStyle(sidebarButtonStyle);
        helpButton.setStyle(sidebarButtonStyle);
        logoutButton.setStyle(sidebarButtonStyle);

        Button[] sidebarButtons = {
                dashboardButton,
                parkingButton,
                aboutButton,
                helpButton,
                logoutButton
        };

        for (Button button : sidebarButtons) {
            button.setOnMouseEntered(e -> button.setStyle(
                    sidebarButtonStyle +
                            "-fx-background-color: rgba(255,255,255,0.15);" +
                            "-fx-background-radius: 8;" +
                            "-fx-padding: 8 12;"
            ));

            button.setOnMouseExited(e -> button.setStyle(sidebarButtonStyle));
        }

        dashboardButton.setMaxWidth(Double.MAX_VALUE);
        parkingButton.setMaxWidth(Double.MAX_VALUE);
        aboutButton.setMaxWidth(Double.MAX_VALUE);
        helpButton.setMaxWidth(Double.MAX_VALUE);

        sidebar.getChildren().addAll(
                appTitle,
                dashboardButton,
                parkingButton,
                aboutButton,
                helpButton,
                logoutButton
        );

        VBox mainContent = new VBox(20);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(40));
        mainContent.setTranslateX(-25);

        Label welcome = new Label("Welcome, Student");
        welcome.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 34px;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-weight: bold;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 12, 0, 0, 2);"
        );
        welcome.setTranslateY(-165);

        Label role = new Label("Logged in as: Student");
        role.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-weight: bold;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 12, 0, 0, 2);"
        );
        role.setTranslateY(-160);

        Button findParking = new Button("Find Parking");
        findParking.setStyle(
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 12 24;" +
                        "-fx-cursor: hand;"
        );
        findParking.setTranslateY(95);

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 13px;");
        messageLabel.setTranslateY(105);
        /**
         * Loads the student dashboard again.
         */
        dashboardButton.setOnAction(e -> {
            Scene dashboardScene = new Scene(new StudentDashboardPage().getView(stage), 1100, 650);
            stage.setScene(dashboardScene);
        });

        /**
         * Opens the parking recommendation flow from the sidebar.
         */
        parkingButton.setOnAction(e -> openRecommendedParking(stage, messageLabel));

        /**
         * Opens the About page.
         */
        aboutButton.setOnAction(e -> {
            Scene aboutScene = new Scene(new AboutPage().getView(stage), 1100, 650);
            stage.setScene(aboutScene);
        });

        /**
         * Opens the Help page.
         */
        helpButton.setOnAction(e -> {
            Scene helpScene = new Scene(new HelpPage().getView(stage), 1100, 650);
            stage.setScene(helpScene);
        });

        /**
         * Returns the user to the login page.
         */
        logoutButton.setOnAction(e -> {
            UserSession.clear(); // clears session

            Scene loginScene = new Scene(new LoginPage().getView(stage), 1100, 650);
            stage.setScene(loginScene);
        });

        /**
         * Event handler for the main Find Parking button.
         *
         * When clicked, it sends a request to the backend to get the
         * recommended parking lot and then displays the result page.
         */
        findParking.setOnAction(e -> openRecommendedParking(stage, messageLabel));

        mainContent.getChildren().addAll(welcome, role, findParking, messageLabel);

        StackPane mainContentWrapper = new StackPane();

        Image image = new Image(getClass().getResource("/ramincar.png").toExternalForm());
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(930);
        imageView.setFitHeight(650);
        imageView.setPreserveRatio(false);
        imageView.setOpacity(0.9);

        mainContentWrapper.getChildren().addAll(imageView, mainContent);

        root.setLeft(sidebar);
        root.setCenter(mainContentWrapper);

        return root;
    }

    /**
     * Opens the parking results page using the recommended lot returned
     * from the backend for a student user.
     *
     * @param stage the primary stage used for navigation
     * @param messageLabel label used to display user-facing error messages
     */
    private void openRecommendedParking(Stage stage, Label messageLabel) {
        try {
            String response = sendRecommendedLotRequest("STUDENT");

            String lotId = extractJsonValue(response, "id");
            String lotName = extractJsonValue(response, "name");
            String spaces = extractJsonValue(response, "availableSpaces");
            String distance = extractJsonValue(response, "distance");
            String status = extractJsonValue(response, "status");

            ParkingResultsPage resultsPage = new ParkingResultsPage();
            Scene resultsScene = new Scene(
                    resultsPage.getView(stage, lotId, lotName, spaces, distance, status, "STUDENT"),
                    1100,
                    650
            );
            stage.setScene(resultsScene);

        } catch (Exception ex) {
            messageLabel.setText("Unable to load parking recommendation.");
            ex.printStackTrace();
        }
    }

    /**
     * Sends a GET request to the backend to retrieve the recommended parking lot.
     *
     * Example endpoint:
     * http://localhost:8080/api/lots/recommended?role=STUDENT
     *
     * @param role the role of the user
     * @return the JSON response body
     * @throws Exception if the request fails
     */
    private String sendRecommendedLotRequest(String role) throws Exception {
        URL url = new URL("http://localhost:8080/api/lots/recommended?role=" + role);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        int responseCode = conn.getResponseCode();

        InputStream inputStream =
                (responseCode == 200) ? conn.getInputStream() : conn.getErrorStream();

        String response;
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            response = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        }

        if (responseCode != 200) {
            throw new Exception("Request failed: " + response);
        }

        return response;
    }

    /**
     * Extracts a value from a simple JSON string using the provided key.
     *
     * This supports string values and numeric values returned by the backend.
     *
     * @param json the JSON response as a string
     * @param key the field to extract
     * @return the extracted value, or an empty string if not found
     */
    private String extractJsonValue(String json, String key) {
        String stringPattern = "\"" + key + "\":\"";
        int start = json.indexOf(stringPattern);

        if (start != -1) {
            start += stringPattern.length();
            int end = json.indexOf("\"", start);
            if (end != -1) {
                return json.substring(start, end);
            }
        }

        String numberPattern = "\"" + key + "\":";
        start = json.indexOf(numberPattern);

        if (start != -1) {
            start += numberPattern.length();
            int end = start;

            while (end < json.length()
                    && json.charAt(end) != ','
                    && json.charAt(end) != '}') {
                end++;
            }

            return json.substring(start, end).trim();
        }

        return "";
    }
}