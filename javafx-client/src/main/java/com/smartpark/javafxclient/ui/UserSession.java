package com.smartpark.javafxclient.ui;

/**
 * UserSession is a simple client-side session manager used to store
 * the currently logged-in user's information during runtime.
 *
 * This class allows different pages in the JavaFX application to access
 * the authenticated user's username and role without repeatedly calling
 * the backend.
 *
 * Since this project uses an in-memory authentication system (no database),
 * this class simulates session persistence on the frontend.
 *
 * NOTE:
 * - Real Mysql database implementation will be worked on as the project goes foward
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

