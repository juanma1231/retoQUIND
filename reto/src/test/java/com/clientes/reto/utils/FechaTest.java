package com.clientes.reto.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FechaTest {
    Fecha fecha;

    @Test
    void calcularEdad() {
        Date fechaNacimiento = new Date(99, 4, 8);
        Integer anios = Fecha.calcularEdad(fechaNacimiento);
        Assertions.assertEquals(24,anios);
    }
}