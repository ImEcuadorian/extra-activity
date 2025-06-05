package io.github.imecuadorian.extra_activity.util;

public class Constants {

    // Email: lowercase start, letters/digits, one '@', and 1–2 domain parts
    public static final String EMAIL_REGEX = "^[a-z][a-z0-9]*@[a-z0-9]+(\\.[a-z]{2,}){1,2}$";

    // DNI: starts with 0–2, 8 digits, hyphen, and 1 digit
    public static final String DNI_REGEX = "^[0-2]\\d{8}-\\d$";

    // Name: 2 to 4 words, each starting with uppercase followed by lowercase letters, no tildes or dots
    public static final String NAMES_REGEX = "^([A-Z][a-z]+)( [A-Z][a-z]+){1,3}$";
}
