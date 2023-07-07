package com.clientes.reto.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class MyConverter {
    private final ConversionService conversionService;

    @Autowired
    public MyConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }


    public Integer convertirStringAInteger(String numeroString) {
        return conversionService.convert(numeroString, Integer.class);
    }
}