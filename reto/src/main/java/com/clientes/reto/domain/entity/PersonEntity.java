package com.clientes.reto.domain.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
public class PersonEntity {

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

    @Column(name = "birth_day")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    @Column(nullable = false)
    private Integer age;


    @Column(name = "creation_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private  Date updateDate;



    public PersonEntity() {
    }

    public PersonEntity( String idType, Integer idNumber, String name, String lastname, String email, Date birthDay, Integer age) {
        this.idType = idType;
        this.idNumber = idNumber;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.birthDay = birthDay;
        this.age = age;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


}
