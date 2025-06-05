package io.github.imecuadorian.extra_activity.controller;

import io.github.imecuadorian.extra_activity.util.Constants;
import io.github.imecuadorian.library.Files;

public class Validator {
    public static boolean isValidName(String name) {
        return Files.validateByRegularExpression(name, Constants.NAMES_REGEX);
    }

    public static boolean isValidDNI(String dni) {
        return Files.validateByRegularExpression(dni, Constants.DNI_REGEX);
    }

    public static boolean isValidEmail(String email) {
        return Files.validateByRegularExpression(email, Constants.EMAIL_REGEX);
    }
}
