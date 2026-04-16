package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * ParkingResultsPage displays the recommended parking lot to the user.
 *
 * This page receives dynamic data from the backend including:
 * - Lot ID
 * - Lot name
 * - Available spaces
 * - Distance from destination
 * - Current status
 *
 * It also allows the user to reserve a parking spot and return
 * to the appropriate dashboard.
 */
public class ParkingResultsPage {

    /**
     * Builds the Parking Results UI using backend data.
     *
     * @param stage the primary stage used for navigation
     * @param lotId the ID of the parking lot
     * @param lotName the name of the recommended lot
     * @param spaces available spaces in the lot
     * @param distance distance to the lot
     * @param status current availability status
     * @param role the user role (STUDENT or FACULTY)
     * @return VBox layout containing the parking results view
     */
    public VBox getView(Stage stage, String lotId, String lotName, String spaces, String distance, String status, String role) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #F5F7F6;");

        Label title = new Label("Parking Results");
        title.setStyle(
                "-fx-font-size: 32px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #0B5E3C;"
        );

        VBox resultsCard = new VBox(15);
        resultsCard.setAlignment(Pos.CENTER);
        resultsCard.setPadding(new Insets(30));
        resultsCard.setMaxWidth(500);
        resultsCard.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 16;" +
                        "-fx-border-color: #D9E2DD;" +
                        "-fx-border-radius: 16;"
        );

        Label lotIdLabel = new Label("Lot ID: " + lotId);
        Label bestLot = new Label("Best Lot: " + lotName);
        Label spacesLabel = new Label("Open Spaces: " + spaces);
        Label distanceLabel = new Label("Distance: " + distance);
        Label statusLabel = new Label("Status: " + status);
        Label messageLabel = new Label();

        lotIdLabel.setStyle("-fx-font-size: 16px; -fx-font-family: 'Arial'; -fx-text-fill: #333333;");
        bestLot.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222222;"
        );
        spacesLabel.setStyle("-fx-font-size: 17px; -fx-font-family: 'Arial'; -fx-text-fill: #333333;");
        distanceLabel.setStyle("-fx-font-size: 17px; -fx-font-family: 'Arial'; -fx-text-fill: #333333;");
        statusLabel.setStyle("-fx-font-size: 17px; -fx-font-family: 'Arial'; -fx-text-fill: #333333;");
        messageLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #cc0000;");

        Button reserveButton = new Button("Reserve Spot");
        Button releaseButton = new Button("Release Spot");
        Button backButton = new Button("Back to Dashboard");

        String buttonStyle =
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 12 24;";

        reserveButton.setStyle(buttonStyle);
        backButton.setStyle(buttonStyle);
        releaseButton.setStyle(buttonStyle);

        reserveButton.setPrefWidth(220);
        backButton.setPrefWidth(220);
        releaseButton.setPrefWidth(220);

        /**
         * Reserves a spot in the selected parking lot and reloads the page
         * with updated lot information from the backend.
         */
        reserveButton.setOnAction(e -> {
            try {
                String response = sendReserveRequest(lotId, role);

                String updatedLotId = extractJsonValue(response, "id");
                String updatedLotName = extractJsonValue(response, "name");
                String updatedSpaces = extractJsonValue(response, "availableSpaces");
                String updatedDistance = extractJsonValue(response, "distance");
                String updatedStatus = extractJsonValue(response, "status");

                messageLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: green;");
                messageLabel.setText("Spot reserved successfully.");

                Scene refreshedScene = new Scene(
                        getView(stage, updatedLotId, updatedLotName, updatedSpaces, updatedDistance, updatedStatus, role),
                        1100,
                        650
                );
                stage.setScene(refreshedScene);

            } catch (Exception ex) {
                messageLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #cc0000;");
                messageLabel.setText("Unable to reserve a spot.");
                ex.printStackTrace();
            }
        });

        /**
         * Releases a reserved parking spot and reloads updated lot info.
         */
        releaseButton.setOnAction(e -> {
            try {
                String response = sendReleaseRequest(lotId, role);

                String updatedLotId = extractJsonValue(response, "id");
                String updatedLotName = extractJsonValue(response, "name");
                String updatedSpaces = extractJsonValue(response, "availableSpaces");
                String updatedDistance = extractJsonValue(response, "distance");
                String updatedStatus = extractJsonValue(response, "status");

                messageLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: green;");
                messageLabel.setText("Spot released successfully.");

                Scene refreshedScene = new Scene(
                        getView(stage, updatedLotId, updatedLotName, updatedSpaces, updatedDistance, updatedStatus, role),
                        1100,
                        650
                );
                stage.setScene(refreshedScene);

            } catch (Exception ex) {
                messageLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #cc0000;");
                messageLabel.setText("Unable to release spot.");
                ex.printStackTrace();
            }
        });


         // Returns the user to the correct dashboard based on role.
        backButton.setOnAction(e -> {
            Scene dashboardScene;

            if ("FACULTY".equalsIgnoreCase(role)) {
                dashboardScene = new Scene(new FacultyDashboardPage().getView(stage), 1100, 650);
            } else {
                dashboardScene = new Scene(new StudentDashboardPage().getView(stage), 1100, 650);
            }

            stage.setScene(dashboardScene);
        });

        resultsCard.getChildren().addAll(
                lotIdLabel,
                bestLot,
                spacesLabel,
                distanceLabel,
                statusLabel,
                reserveButton,
                releaseButton,
                backButton,
                messageLabel
        );

        root.getChildren().addAll(title, resultsCard);

        return root;
    }

    /**
     * Sends a POST request to reserve a parking spot in the selected lot.
     *
     * @param lotId the ID of the lot
     * @param role the user's role
     * @return backend response as JSON
     * @throws Exception if the request fails
     */
    private String sendReserveRequest(String lotId, String role) throws Exception {
        URL url = new URL(
                "http://localhost:8080/api/lots/" + lotId
                        + "/reserve?role=" + role
                        + "&username=" + UserSession.getUsername()
        );
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(new byte[0]);
            os.flush();
        }

        int responseCode = conn.getResponseCode();

        InputStream inputStream =
                (responseCode >= 200 && responseCode < 300)
                        ? conn.getInputStream()
                        : conn.getErrorStream();

        String response;
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            response = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        }

        if (responseCode < 200 || responseCode >= 300) {
            throw new Exception("Request failed: " + response);
        }

        return response;
    }

    /**
     * Sends a POST request to release a parking spot in the selected lot.
     *
     * @param lotId the ID of the lot
     * @param role the user's role
     * @return backend response as JSON
     * @throws Exception if the request fails
     */
    private String sendReleaseRequest(String lotId, String role) throws Exception {
        URL url = new URL(
                "http://localhost:8080/api/lots/" + lotId
                        + "/release?role=" + role
                        + "&username=" + UserSession.getUsername()
        );
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(new byte[0]);
            os.flush();
        }

        int responseCode = conn.getResponseCode();

        InputStream inputStream =
                (responseCode >= 200 && responseCode < 300)
                        ? conn.getInputStream()
                        : conn.getErrorStream();

        String response;
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            response = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        }

        if (responseCode < 200 || responseCode >= 300) {
            throw new Exception("Request failed: " + response);
        }

        return response;
    }

    /**
     * Extracts a value from a JSON string using the given key.
     * Supports both string and numeric values.
     *
     * @param json the JSON response
     * @param key the field to extract
     * @return extracted value or empty string if not found
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