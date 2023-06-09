package com.metagain.frontend.model.storage;

public class ProfileDataStorage {

    private static String username;

    private static String password;

    private static String email;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ProfileDataStorage.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ProfileDataStorage.password = password;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ProfileDataStorage.email = email;
    }
}
