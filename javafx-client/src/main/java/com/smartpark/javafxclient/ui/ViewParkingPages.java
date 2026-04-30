package com.smartpark.javafxclient.ui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartpark.javafxclient.dto.ParkingLotDTO;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ViewParkingPages {

    // Holds all parking lot cards
    private final VBox lotList = new VBox(15);

    public BorderPane getView(Stage stage) {

        BorderPane root = new BorderPane();

        // Sidebar
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
        Button logoutButton = new Button("Logout");

        dashboardButton.setStyle(sidebarButtonStyle);
        logoutButton.setStyle(sidebarButtonStyle);

        dashboardButton.setMaxWidth(Double.MAX_VALUE);
        logoutButton.setMaxWidth(Double.MAX_VALUE);

        sidebar.getChildren().addAll(
                appTitle,
                dashboardButton,
                logoutButton
        );

        // Main content
        VBox content = new VBox(20);
        content.setPadding(new Insets(40));
        content.setStyle("-fx-background-color: #F4F6F5;");

        Label title = new Label("Parking Lots");
        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1F2937;"
        );

        Label subtitle = new Label("Current parking lot availability from the database.");
        subtitle.setStyle("-fx-font-size: 15px; -fx-text-fill: #6B7280;");

        // ScrollPane allows multiple parking lots without breaking layout
        ScrollPane scrollPane = new ScrollPane(lotList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        content.getChildren().addAll(title, subtitle, scrollPane);

        dashboardButton.setOnAction(e -> {
            Scene dashboardScene = new Scene(new AdminPage().getView(stage), 1100, 650);
            stage.setScene(dashboardScene);
        });

        logoutButton.setOnAction(e -> {
            UserSession.clear(); // clears session

            Scene loginScene = new Scene(new LoginPage().getView(stage), 1100, 650);
            stage.setScene(loginScene);
        });

        root.setLeft(sidebar);
        root.setCenter(content);

        // Load lots from backend
        loadParkingLots();

        return root;
    }

    /*
     * Retrieves parking lot data from Spring Boot backend.
     *
     * Process:
     * 1. Send HTTP GET request
     * 2. Receive JSON response
     * 3. Convert JSON → ParkingLotDTO objects
     * 4. Update UI with parking lot cards
     *
     * Uses async call so UI does not freeze.
     */
    private void loadParkingLots() {

        HttpClient client = HttpClient.newHttpClient();

        // Build HTTP request to backend endpoint
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/lots"))
                .GET()
                .build();

        ObjectMapper mapper = new ObjectMapper();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)

                // Convert JSON response into Java objects
                .thenApply(body -> {
                    try {
                        return mapper.readValue(
                                body,
                                new TypeReference<List<ParkingLotDTO>>() {}
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })

                // Update UI safely using JavaFX thread
                .thenAccept(lots -> Platform.runLater(() -> {
                    lotList.getChildren().clear();

                    for (ParkingLotDTO lot : lots) {
                        lotList.getChildren().add(createLotCard(lot));
                    }
                }))

                // Handle errors (backend down, bad response, etc)
                .exceptionally(e -> {
                    Platform.runLater(() -> {
                        lotList.getChildren().clear();

                        Label error = new Label("Could not load parking lots from the database.");
                        error.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");

                        lotList.getChildren().add(error);
                    });

                    e.printStackTrace();
                    return null;
                });
    }

    /*
     * Creates a UI "card" for a parking lot.
     *
     * Displays:
     * - Name
     * - Available spaces
     * - Allowed role
     * - Distance
     * - Status (color-coded)
     */
    private HBox createLotCard(ParkingLotDTO lot) {

        // Main card container
        HBox card = new HBox(25);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(18));
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: #D1D5DB;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.08), 8, 0, 0, 2);"
        );

        // Vertical layout for text
        VBox info = new VBox(6);

        // Parking lot name
        Label name = new Label(lot.getName());
        name.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        // Available spaces
        Label spaces = new Label(
                lot.getAvailableSpaces() + " of " + lot.getCapacity() + " spaces available"
        );
        spaces.setStyle("-fx-font-size: 15px; -fx-text-fill: #374151;");

        // Role restriction
        Label role = new Label("Allowed Role: " + lot.getAllowedRole());
        role.setStyle("-fx-font-size: 14px; -fx-text-fill: #6B7280;");

        // Distance info
        Label distance = new Label("Distance: " + lot.getDistance() + " miles");
        distance.setStyle("-fx-font-size: 14px; -fx-text-fill: #6B7280;");

        // Status label (color-coded)
        Label status = new Label("Status: " + lot.getStatus());
        status.setStyle(getStatusStyle(lot.getStatus()));

        info.getChildren().addAll(name, spaces, role, distance, status);
        card.getChildren().add(info);

        return card;
    }

    /*
     * Returns CSS styling based on parking lot status.
     *
     * Color coding:
     * - Green = Available/Open
     * - Orange = Limited
     * - Red = Full
     * - Gray = Unknown
     */
    private String getStatusStyle(String status) {

        if (status == null) {
            return "-fx-font-size: 14px; -fx-text-fill: #6B7280;";
        }

        return switch (status.toUpperCase()) {
            case "AVAILABLE", "OPEN" ->
                    "-fx-font-size: 14px; -fx-text-fill: #0B5E3C; -fx-font-weight: bold;";
            case "LIMITED" ->
                    "-fx-font-size: 14px; -fx-text-fill: #D97706; -fx-font-weight: bold;";
            case "FULL" ->
                    "-fx-font-size: 14px; -fx-text-fill: #B91C1C; -fx-font-weight: bold;";
            default ->
                    "-fx-font-size: 14px; -fx-text-fill: #6B7280;";
        };
    }
}
