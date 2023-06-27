package com.clientes.reto.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {

    private Integer id;
    private String transaccionType;

    private String description;

    private double monto;
    private ProductDto productDto;
}
