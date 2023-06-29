package com.clientes.reto.utils;

public final class UtilString {
    public static final String EMPTY = "";
    public static final String SPACE = " ";

    public static void requiresPattern(String s, String pattern, String message) {
        if (!s.matches(pattern)) {
            throw new IllegalArgumentException(message);
        }
    }

    private UtilString() {
    }

    public static void requieresLength(String s, int lengthMin, int lengthMax, String message) {
        if (!(s.length() >= lengthMin && s.length() <= lengthMax)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isEmptyOrNull(String val) {

        return val == null || val.trim().isEmpty();
    }
    public static boolean isEmptyOrNull(Integer val) {

        return val == null;
    }

    public static void requieresNoNullOrNoEmpty(String val, String mensaje) {
        if (isEmptyOrNull(val)) {
            throw new IllegalArgumentException(mensaje);
        }

    }
}