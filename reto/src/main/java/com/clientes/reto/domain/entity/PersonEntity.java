package com.clientes.reto.domain.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "person")
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

    @Column(name = "birth_day", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime birthDay;

    @Column(nullable = false)
    private Integer age;


    @Column(name = "creation_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime creationDate;

    @Column(name = "update_date", nullable = false, columnDefinition = "DATETIME")
    private  LocalDateTime updateDate;

    @OneToMany(mappedBy = "client")
    private List<ProductEntity> products;

    public PersonEntity() {
    }

    public PersonEntity(Integer id, String idType, Integer idNumber, String name, String lastname, String email, LocalDateTime birthDay, Integer age, LocalDateTime creationDate, LocalDateTime updateDate, List<ProductEntity> products) {
        this.id = id;
        this.idType = idType;
        this.idNumber = idNumber;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.birthDay = birthDay;
        this.age = age;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDateTime birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
