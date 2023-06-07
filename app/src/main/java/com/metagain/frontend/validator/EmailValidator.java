package com.metagain.frontend.validator;

import android.text.TextUtils;
import android.util.Patterns;

public class EmailValidator {

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
