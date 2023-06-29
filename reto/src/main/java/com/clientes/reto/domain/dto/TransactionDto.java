package com.clientes.reto.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class TransactionDto {

    private Integer id;
    private String transaccionType;

    private String description;

    private double monto;
    private ProductDto productDto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransaccionType() {
        return transaccionType;
    }

    public void setTransaccionType(String transaccionType) {
        this.transaccionType = transaccionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }
}
