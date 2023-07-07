package com.clientes.reto.utils;

import java.util.Random;


public class NumeroCuentaGenerator {
    private static Random random = new Random();

    public static String generarNumeroCuentaCorriente() {
        String inicio = "23";
        String numeroCuenta = generarNumeroCuenta();
        return inicio + numeroCuenta;
    }

    public static String generarNumeroCuentaAhorro() {
        String inicio = "46";
        String numeroCuenta = generarNumeroCuenta();
        return inicio + numeroCuenta;
    }

    private static String generarNumeroCuenta() {
        String numeroCuenta;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        numeroCuenta = sb.toString();
        return numeroCuenta;
    }

}