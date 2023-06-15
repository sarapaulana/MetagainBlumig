package com.metagain.frontend.model.storage;

import com.metagain.frontend.model.OwnProfile;

public class ProfileDataStorage {

    private static String username;

    private static String password;

    private static String email;

    private static boolean incognito;

    private static OwnProfile ownProfile;

    private static double latitude;

    private static double longitude;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ProfileDataStorage.username = username;
        ownProfile.setUsername(username);
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ProfileDataStorage.password = password;
        ownProfile.setPassword(password);
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ProfileDataStorage.email = email;
        ownProfile.setEmail(email);
    }

    public static OwnProfile getOwnProfile() {
        return ownProfile;
    }

    public static void setOwnProfile(OwnProfile ownProfile) {
        ProfileDataStorage.ownProfile = ownProfile;
        ProfileDataStorage.username = ownProfile.getUsername();
        ProfileDataStorage.password = ownProfile.getPassword();
        ProfileDataStorage.email = ownProfile.getEmail();
        ProfileDataStorage.incognito = ownProfile.isIncognito();
    }

    public static double getLatitude() {
        return latitude;
    }

    public static boolean isIncognito() {
        return incognito;
    }

    public static void setIncognito(boolean incognito) {
        ProfileDataStorage.incognito = incognito;
        ownProfile.setIncognito(incognito);
    }

    public static void setLatitude(double latitude) {
        ProfileDataStorage.latitude = latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        ProfileDataStorage.longitude = longitude;
    }
}
