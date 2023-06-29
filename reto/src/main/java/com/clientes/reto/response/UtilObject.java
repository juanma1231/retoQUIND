package com.clientes.reto.response;

public class UtilObject {
    private static UtilObject instance = new UtilObject();

    private UtilObject() {

    }

    public static UtilObject getUtilObject() {
        return instance;
    }
    public <T> boolean isNull(T object) {
        return object== null;
    }
    public <T> boolean isNullOrEmpty(T object) {
        return object== null || object == "" || object == " ";
    }
    public <T> T getDefault(T object, T defaultValue) {
        return isNull(object) ? defaultValue : object ;
    }
}
