package com.metagain.frontend.model.storage;

import com.metagain.frontend.model.OwnProfile;

public class ProfileDataStorage {

    private static String username;

    private static String password;

    private static String email;

    private static OwnProfile ownProfile;

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
    }
}
