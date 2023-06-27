package com.clientes.reto.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class PersonDto {
    private Integer id;
    private String idType;
    private Integer idNumber;
    private  String name;
    private  String lastName;
    private  String email;
    private Data birthDay;
    private Integer age;
    private  Data creationDate;
    private  Data updateDate;
    private List<ProductDto> products;
}
