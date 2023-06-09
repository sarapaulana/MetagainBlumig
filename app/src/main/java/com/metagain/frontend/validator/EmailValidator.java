package com.metagain.frontend.validator;

import java.util.regex.Pattern;

public class EmailValidator {

    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static boolean isValidEmail(String target) {
        return (target != null && target.trim().length() > 0 && EMAIL_ADDRESS.matcher(target).matches());
    }

}
