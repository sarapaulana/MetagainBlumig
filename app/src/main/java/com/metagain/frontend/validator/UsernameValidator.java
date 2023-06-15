package com.metagain.frontend.validator;

import java.util.regex.Pattern;

/*
Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.
Username allowed of the dot (.), underscore (_), and hyphen (-).
The dot (.), underscore (_), or hyphen (-) must not be the first or last character.
The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex
The number of characters must be between 5 to 20.
 */
public class UsernameValidator {

    public static final Pattern USERNAME = Pattern.compile(
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$");

    public static boolean isValidUsername(String username) {
        return (username != null  && USERNAME.matcher(username).matches());
    }

}
