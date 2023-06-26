package com.clientes.reto.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class PersonEntity {

    @Column(name = "person_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_type", nullable = false)
    private String idType;

    @Column(name = "id_number", nullable = false)
    private Integer idNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Id
    @Column(nullable = false)
    private String email;

    @Column(name = "birth_day", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Data birthDay;

    @Column(nullable = false)
    private Integer age;


    @Column(name = "creation_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Data creationDate;

    @Column(name = "update_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private  Data updateDate;

    @OneToMany(mappedBy = "client")
    private List<ProductEntity> products;



}
