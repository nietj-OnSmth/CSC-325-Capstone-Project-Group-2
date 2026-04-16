package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * AdminEditPage allows administrator users to manage parking lots.
 *
 * This page supports CRUD-related admin actions by communicating
 * with the Spring Boot backend API.
 *
 * Admins can:
 * - Add a new parking lot
 * - Update an existing lot
 * - Delete a lot by ID
 * - Refresh and view all lots
 * - Return to the Admin Dashboard
 */
public class AdminEditPage {

    /**
     * Builds and returns the Admin Edit Page UI.
     *
     * This method creates the form fields, action buttons, and results area
     * used by admins to manage parking lot data.
     *
     * @param stage the primary JavaFX stage used for scene switching
     * @return the root BorderPane for the admin edit page
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
                        "-fx-padding: 0;" +
                        "-fx-cursor: hand;" +
                        "-fx-border-color: transparent;";

        Button dashboardButton = new Button("Dashboard");
        Button aboutButton = new Button("About");
        Button helpButton = new Button("Help");
        Button logoutButton = new Button("Logout");

        dashboardButton.setStyle(sidebarButtonStyle);
        aboutButton.setStyle(sidebarButtonStyle);
        helpButton.setStyle(sidebarButtonStyle);
        logoutButton.setStyle(sidebarButtonStyle);

        dashboardButton.setMaxWidth(Double.MAX_VALUE);
        aboutButton.setMaxWidth(Double.MAX_VALUE);
        helpButton.setMaxWidth(Double.MAX_VALUE);
        logoutButton.setMaxWidth(Double.MAX_VALUE);

        sidebar.getChildren().addAll(
                appTitle,
                dashboardButton,
                aboutButton,
                helpButton,
                logoutButton
        );

        VBox mainContent = new VBox(20);
        mainContent.setAlignment(Pos.TOP_CENTER);
        mainContent.setPadding(new Insets(35));
        mainContent.setStyle("-fx-background-color: #F5F7F6;");

        Label title = new Label("Lot Management System");
        title.setStyle(
                "-fx-font-size: 30px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #0B5E3C;"
        );

        VBox formCard = new VBox(12);
        formCard.setAlignment(Pos.CENTER_LEFT);
        formCard.setPadding(new Insets(25));
        formCard.setMaxWidth(550);
        formCard.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 16;" +
                        "-fx-border-color: #D9E2DD;" +
                        "-fx-border-radius: 16;"
        );

        Label formTitle = new Label("Manage Parking Lots");
        formTitle.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222222;"
        );

        TextField idField = new TextField();
        idField.setPromptText("Lot ID (required for update/delete)");

        TextField nameField = new TextField();
        nameField.setPromptText("Lot Name");

        TextField roleField = new TextField();
        roleField.setPromptText("Allowed Role (STUDENT / FACULTY / ADMIN)");

        TextField capacityField = new TextField();
        capacityField.setPromptText("Capacity");

        TextField spacesField = new TextField();
        spacesField.setPromptText("Available Spaces");

        TextField distanceField = new TextField();
        distanceField.setPromptText("Distance (example: 150.0)");

        String fieldStyle =
                "-fx-background-radius: 10;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 10;";

        idField.setStyle(fieldStyle);
        nameField.setStyle(fieldStyle);
        roleField.setStyle(fieldStyle);
        capacityField.setStyle(fieldStyle);
        spacesField.setStyle(fieldStyle);
        distanceField.setStyle(fieldStyle);

        idField.setMaxWidth(400);
        nameField.setMaxWidth(400);
        roleField.setMaxWidth(400);
        capacityField.setMaxWidth(400);
        spacesField.setMaxWidth(400);
        distanceField.setMaxWidth(400);

        HBox actionRow1 = new HBox(12);
        HBox actionRow2 = new HBox(12);
        actionRow1.setAlignment(Pos.CENTER_LEFT);
        actionRow2.setAlignment(Pos.CENTER_LEFT);

        Button addLotButton = new Button("Add Lot");
        Button updateLotButton = new Button("Update Lot");
        Button deleteLotButton = new Button("Delete Lot");
        Button backButton = new Button("Back to Dashboard");

        String buttonStyle =
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10 20;" +
                        "-fx-cursor: hand;";

        addLotButton.setStyle(buttonStyle);
        updateLotButton.setStyle(buttonStyle);
        deleteLotButton.setStyle(buttonStyle);
        backButton.setStyle(buttonStyle);

        addLotButton.setPrefWidth(130);
        updateLotButton.setPrefWidth(130);
        deleteLotButton.setPrefWidth(130);
        backButton.setPrefWidth(180);

        actionRow1.getChildren().addAll(addLotButton, updateLotButton, deleteLotButton);
        actionRow2.getChildren().addAll(backButton);

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: #cc0000; -fx-font-size: 13px;");

        formCard.getChildren().addAll(
                formTitle,
                idField,
                nameField,
                roleField,
                capacityField,
                spacesField,
                distanceField,
                actionRow1,
                actionRow2,
                messageLabel
        );


        // Reloads the admin dashboard.

        dashboardButton.setOnAction(e -> {
            Scene dashboardScene = new Scene(new AdminPage().getView(stage), 1100, 650);
            stage.setScene(dashboardScene);
        });


         // Opens the About page.

        aboutButton.setOnAction(e -> {
            Scene aboutScene = new Scene(new AboutPage().getView(stage), 1100, 650);
            stage.setScene(aboutScene);
        });


         // Opens the Help page.

        helpButton.setOnAction(e -> {
            Scene helpScene = new Scene(new HelpPage().getView(stage), 1100, 650);
            stage.setScene(helpScene);
        });


         // Returns the user to the login page.
        logoutButton.setOnAction(e -> {
            Scene loginScene = new Scene(new LoginPage().getView(stage), 1100, 650);
            stage.setScene(loginScene);
        });

         // Returns the user to the Admin Dashboard.
        backButton.setOnAction(e -> {
            Scene dashboardScene = new Scene(new AdminPage().getView(stage), 1100, 650);
            stage.setScene(dashboardScene);
        });


         // Sends a POST request to create a new parking lot.

        addLotButton.setOnAction(e -> {
            try {
                String validationError = validateLotFieldsForCreateOrUpdate(
                        nameField, roleField, capacityField, spacesField, distanceField
                );
                if (validationError != null) {
                    messageLabel.setText(validationError);
                    return;
                }

                int capacity = Integer.parseInt(capacityField.getText().trim());
                int spaces = Integer.parseInt(spacesField.getText().trim());
                double distance = Double.parseDouble(distanceField.getText().trim());
                String status = determineLotStatus(spaces, capacity);

                String jsonBody = "{"
                        + "\"name\":\"" + escapeJson(nameField.getText().trim()) + "\","
                        + "\"allowedRole\":\"" + roleField.getText().trim().toUpperCase() + "\","
                        + "\"capacity\":" + capacity + ","
                        + "\"availableSpaces\":" + spaces + ","
                        + "\"distance\":" + distance + ","
                        + "\"status\":\"" + status + "\""
                        + "}";

                sendRequest("POST", "http://localhost:8080/api/lots?role=ADMIN", jsonBody);

                messageLabel.setStyle("-fx-text-fill: green; -fx-font-size: 13px;");
                messageLabel.setText("Lot added successfully.");
                clearFields(idField, nameField, roleField, capacityField, spacesField, distanceField);

            } catch (Exception ex) {
                messageLabel.setStyle("-fx-text-fill: #cc0000; -fx-font-size: 13px;");
                messageLabel.setText("Failed to add lot.");
                ex.printStackTrace();
            }
        });


        // Sends a PUT request to update an existing parking lot.
        updateLotButton.setOnAction(e -> {
            try {
                if (idField.getText().trim().isEmpty()) {
                    messageLabel.setText("Lot ID is required for update.");
                    return;
                }

                String validationError = validateLotFieldsForCreateOrUpdate(
                        nameField, roleField, capacityField, spacesField, distanceField
                );
                if (validationError != null) {
                    messageLabel.setText(validationError);
                    return;
                }

                long id = Long.parseLong(idField.getText().trim());
                int capacity = Integer.parseInt(capacityField.getText().trim());
                int spaces = Integer.parseInt(spacesField.getText().trim());
                double distance = Double.parseDouble(distanceField.getText().trim());
                String status = determineLotStatus(spaces, capacity);

                String jsonBody = "{"
                        + "\"name\":\"" + escapeJson(nameField.getText().trim()) + "\","
                        + "\"allowedRole\":\"" + roleField.getText().trim().toUpperCase() + "\","
                        + "\"capacity\":" + capacity + ","
                        + "\"availableSpaces\":" + spaces + ","
                        + "\"distance\":" + distance + ","
                        + "\"status\":\"" + status + "\""
                        + "}";

                sendRequest("PUT", "http://localhost:8080/api/lots/" + id + "?role=ADMIN", jsonBody);

                messageLabel.setStyle("-fx-text-fill: green; -fx-font-size: 13px;");
                messageLabel.setText("Lot updated successfully.");

            } catch (Exception ex) {
                messageLabel.setStyle("-fx-text-fill: #cc0000; -fx-font-size: 13px;");
                messageLabel.setText("Failed to update lot.");
                ex.printStackTrace();
            }
        });


         // Sends a DELETE request to remove a parking lot by ID.
        deleteLotButton.setOnAction(e -> {
            try {
                if (idField.getText().trim().isEmpty()) {
                    messageLabel.setText("Lot ID is required for delete.");
                    return;
                }

                long id = Long.parseLong(idField.getText().trim());
                sendRequest("DELETE", "http://localhost:8080/api/lots/" + id + "?role=ADMIN", null);

                messageLabel.setStyle("-fx-text-fill: green; -fx-font-size: 13px;");
                messageLabel.setText("Lot deleted successfully.");
                clearFields(idField, nameField, roleField, capacityField, spacesField, distanceField);

            } catch (Exception ex) {
                messageLabel.setStyle("-fx-text-fill: #cc0000; -fx-font-size: 13px;");
                messageLabel.setText("Failed to delete lot.");
                ex.printStackTrace();
            }
        });

        mainContent.getChildren().addAll(title, formCard);

        root.setLeft(sidebar);
        root.setCenter(mainContent);

        return root;
    }

    /**
     * Sends an HTTP request to the backend API and returns the response body.
     *
     * @param method the HTTP method (GET, POST, PUT, DELETE)
     * @param endpoint the backend URL
     * @param jsonBody JSON body for POST/PUT requests, or null when not needed
     * @return the backend response as a string
     * @throws Exception if the request fails
     */
    private String sendRequest(String method, String endpoint, String jsonBody) throws Exception {
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(method);
        conn.setRequestProperty("Accept", "application/json");

        if ("POST".equals(method) || "PUT".equals(method)) {
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonBody.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }
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
     * Validates required fields for add/update operations.
     *
     * @return an error message if validation fails, otherwise null
     */
    private String validateLotFieldsForCreateOrUpdate(
            TextField nameField,
            TextField roleField,
            TextField capacityField,
            TextField spacesField,
            TextField distanceField
    ) {
        if (nameField.getText().trim().isEmpty()
                || roleField.getText().trim().isEmpty()
                || capacityField.getText().trim().isEmpty()
                || spacesField.getText().trim().isEmpty()
                || distanceField.getText().trim().isEmpty()) {
            return "Please fill in all required fields.";
        }

        String role = roleField.getText().trim().toUpperCase();
        if (!role.equals("STUDENT") && !role.equals("FACULTY") && !role.equals("ADMIN")) {
            return "Allowed role must be STUDENT, FACULTY, or ADMIN.";
        }

        try {
            int capacity = Integer.parseInt(capacityField.getText().trim());
            int spaces = Integer.parseInt(spacesField.getText().trim());
            Double.parseDouble(distanceField.getText().trim());

            if (capacity <= 0) {
                return "Capacity must be greater than 0.";
            }

            if (spaces < 0) {
                return "Available spaces cannot be negative.";
            }

            if (spaces > capacity) {
                return "Available spaces cannot exceed capacity.";
            }
        } catch (NumberFormatException ex) {
            return "Capacity, spaces, and distance must be valid numbers.";
        }

        return null;
    }

    /**
     * Determines the parking lot status based on available spaces and capacity.
     *
     * This mirrors the backend status logic:
     * - 0 spaces = FULL
     * - 20% or less available = LIMITED
     * - otherwise = AVAILABLE
     *
     * @param availableSpaces current available spaces
     * @param capacity total lot capacity
     * @return the calculated lot status
     */
    private String determineLotStatus(int availableSpaces, int capacity) {
        if (availableSpaces <= 0) {
            return "FULL";
        }

        double percentageAvailable = (double) availableSpaces / capacity;

        if (percentageAvailable <= 0.20) {
            return "LIMITED";
        }

        return "AVAILABLE";
    }


    // Clears all form fields after a successful action.

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    /**
     * Escapes quotation marks in text values before inserting them into JSON.
     *
     * @param value the raw string value
     * @return the escaped string
     */
    private String escapeJson(String value) {
        return value.replace("\"", "\\\"");
    }
}