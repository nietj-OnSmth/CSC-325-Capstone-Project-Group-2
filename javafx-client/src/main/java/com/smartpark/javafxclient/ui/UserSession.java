package com.smartpark.javafxclient.ui;

/**
 * UserSession is a simple client-side session manager used to store
 * the currently logged-in user's information during runtime.
 *
 * This class allows different pages in the JavaFX application to access
 * the authenticated user's username and role without repeatedly calling
 * the backend.
 *
 * The backend now validates users through the MySQL database, but the
 * frontend still needs to temporarily store session information while
 * the application is running.
 *
 * NOTE:
 * - This is a lightweight frontend session approach for the capstone project.
 * - A real production system would use a more secure session or token-based system.
 */
public class UserSession {
    private static String username;
    private static String role;

    public static void setUsername(String username) {
        UserSession.username = username;
    }

    public static String getUsername() {
        return username;
    }

    public static void setRole(String role) {
        UserSession.role = role;
    }

    public static String getRole() {
        return role;
    }

    public static void clear() {
        username = null;
        role = null;
    }
}

