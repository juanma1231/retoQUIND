package com.clientes.reto.domain.service;

import com.clientes.reto.persistence.entity.PersonEntity;
import com.clientes.reto.persistence.entity.ProductEntity;
import com.clientes.reto.persistence.repository.PersonRepository;
import com.clientes.reto.persistence.repository.ProductRepository;
import com.clientes.reto.utils.CustomException;
import com.clientes.reto.utils.Fecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clientes.reto.utils.UtilString;

import java.util.Date;
import java.util.List;


@Service
public class PersonService{

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ProductRepository productRepository;

    public Iterable<PersonEntity> getAllUsers(){
        return personRepository.findAll();
    }

    public  PersonEntity create(PersonEntity personEntity){
        PersonEntity person = personRepository.findOneById(personEntity.getEmail());
        Date fechaActual = new Date();
        personEntity.setAge(Fecha.calcularEdad(personEntity.getBirthDay()));
        if(person==null){
            if (personEntity.getAge() >= 18){
                personEntity.setCreationDate(fechaActual);
                personEntity.setUpdateDate(fechaActual);
                return personRepository.save(personEntity);
            }
            else throw new CustomException("Debe ser mayor a 18 años");
        }else throw new CustomException("El usuario ya existe");

    }
    public void delete(String email){
        List<ProductEntity> products = productRepository.finByUser(email);
        boolean deudas= false;
        for(ProductEntity product:products){
            if (product.getDeaudas()){
                deudas= true;
            }
        }
        if(!deudas){
            personRepository.deleteById(email);
        }
        else throw new CustomException("se encontraron deudas en sus cuentas asociadas");
    }
    public  PersonEntity findById(String email){
        return personRepository.findOneById(email);
    }
    public PersonEntity patch(String email, PersonEntity person){
        PersonEntity person1 = personRepository.findOneById(email);
        person1.setName(UtilString.isEmptyOrNull(person.getName()) ? person1.getName() : person.getName());
        person1.setLastname(UtilString.isEmptyOrNull(person.getLastname()) ? person1.getLastname() : person.getLastname());
        person1.setIdType(UtilString.isEmptyOrNull(person.getIdType()) ? person1.getIdType() : person.getIdType());
        person1.setIdNumber(UtilString.isEmptyOrNull(person.getIdNumber()) ? person1.getIdNumber() : person.getIdNumber());
        Date fechaActual = new Date();
        person1.setUpdateDate(fechaActual);
        return personRepository.save(person1);
    }
    public PersonEntity save(PersonEntity person){
        return personRepository.save(person);
    }
}
