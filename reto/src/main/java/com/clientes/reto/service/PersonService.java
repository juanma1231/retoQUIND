package com.clientes.reto.service;

import com.clientes.reto.domain.entity.PersonEntity;
import com.clientes.reto.domain.entity.ProductEntity;
import com.clientes.reto.repository.PersonRepository;
import com.clientes.reto.repository.ProductRepository;
import com.clientes.reto.response.CustomException;
import com.clientes.reto.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clientes.reto.response.UtilString;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService{

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ProductRepository productRepository;

    public Iterable<PersonEntity> getAllUsers(){
        return personRepository.findAll();
    }

    public  PersonEntity save(PersonEntity personEntity){
        if (personEntity.getAge() >= 18){
            return personRepository.save(personEntity);
        }
        else throw new CustomException("Debe ser mayor a 18 años");
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
        return personRepository.findById(email).orElseThrow(()-> new CustomException("persona no encontrada"));
    }
    public PersonEntity patch(String email, PersonEntity person){
        PersonEntity person1 = personRepository.findOneById(email);
        person1.setName(UtilString.isEmptyOrNull(person.getName()) ? person1.getName() : person.getName());
        person1.setLastname(UtilString.isEmptyOrNull(person.getLastname()) ? person1.getLastname() : person.getLastname());
        person1.setEmail(UtilString.isEmptyOrNull(person.getEmail()) ? person1.getEmail() : person.getEmail());
        person1.setIdType(UtilString.isEmptyOrNull(person.getIdType()) ? person1.getIdType() : person.getIdType());
        person1.setIdNumber(UtilString.isEmptyOrNull(person.getIdNumber()) ? person1.getIdNumber() : person.getIdNumber());

        return personRepository.save(person1);
    }
}
