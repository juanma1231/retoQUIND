package com.clientes.reto.utils;


import java.util.Date;

public class Fecha {
    public static Integer calcularEdad(Date fechaNacimiento){
        Date fechaActual = new Date();
        long diferenciaEnMillis = fechaActual.getTime() - fechaNacimiento.getTime();
        long milisegundosEnUnAnio = 1000L * 60L * 60L * 24L * 365L;
        long edadEnAnios = diferenciaEnMillis / milisegundosEnUnAnio;
        Integer edadAnios = Integer.valueOf((int) edadEnAnios);
        return edadAnios;
    }
}
