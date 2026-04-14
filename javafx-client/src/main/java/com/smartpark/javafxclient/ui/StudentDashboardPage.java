package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
 * - Navigate to parking results based on availability and distance
 *
 * This class is part of the JavaFX frontend and communicates with the
 * Spring Boot backend via HTTP requests.
 */

public class StudentDashboardPage {

    /**
     * Builds and returns the Student Dashboard UI.
     *
     * This method creates the layout, sidebar navigation, and main content area.
     * It also attaches event handlers to buttons, including the "Find Parking"
     * button which triggers a backend API call.
     *
     * @param stage the primary JavaFX stage used for scene navigation
     * @return the root layout (BorderPane) for the student dashboard
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

        Label dashboard = new Label("Dashboard");
        Label parking = new Label("Parking");
        Label about = new Label("About");
        Label help = new Label("Help");

        String sidebarStyle =
                "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Helvetica';";

        dashboard.setStyle(sidebarStyle);
        parking.setStyle(sidebarStyle);
        about.setStyle(sidebarStyle);
        help.setStyle(sidebarStyle);

        sidebar.getChildren().addAll(appTitle, dashboard, parking, about, help);

        VBox mainContent = new VBox(20);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(40));

        Label welcome = new Label("Welcome, Student");
        welcome.setStyle(
                "-fx-font-size: 30px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;"
        );

        Label role = new Label("Logged in as: Student");
        role.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Helvetica';"
        );

        Button findParking = new Button("Find Parking");
        findParking.setStyle(
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 12 24;" +
                        "-fx-cursor: hand;"
        );

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 13px;");

        /**
         * Event handler for the "Find Parking" button.
         *
         * When clicked:
         * 1. Sends a request to the backend to get the recommended parking lot
         * 2. Parses the JSON response
         * 3. Extracts lot details (name, spaces, distance, status)
         * 4. Navigates to the ParkingResultsPage to display results
         */
        findParking.setOnAction(e -> {
            try {
                String response = sendRecommendedLotRequest("STUDENT");

                String lotName = extractJsonValue(response, "name");
                String spaces = extractJsonValue(response, "availableSpaces");
                String distance = extractJsonValue(response, "distance");
                String status = extractJsonValue(response, "status");

                ParkingResultsPage resultsPage = new ParkingResultsPage();
                Scene resultsScene = new Scene(
                        resultsPage.getView(stage, lotName, spaces, distance, status),
                        1100,
                        650
                );
                stage.setScene(resultsScene);

            } catch (Exception ex) {
                messageLabel.setText("Unable to load parking recommendation.");
                ex.printStackTrace();
            }
        });

        mainContent.getChildren().addAll(welcome, role, findParking, messageLabel);

        root.setLeft(sidebar);
        root.setCenter(mainContent);

        return root;
    }

    /**
     * Sends a GET request to the backend to retrieve the recommended parking lot.
     *
     * The backend uses a recommendation strategy (distance + availability)
     * to determine the best lot for the given role.
     *
     * Example endpoint:
     * http://localhost:8080/api/lots/recommended?role=STUDENT
     *
     * @param role the role of the user (STUDENT)
     * @return JSON response containing parking lot data
     * @throws Exception if the request fails or the server returns an error
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
     * Extracts a value from a JSON string based on the given key.
     *
     * This method supports both:
     * - String values (e.g., "name":"Lot A")
     * - Numeric values (e.g., "availableSpaces":24)
     *
     * @param json the JSON response as a string
     * @param key the field to extract
     * @return the extracted value as a string, or empty if not found
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