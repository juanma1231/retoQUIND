package com.clientes.reto.service;

import com.clientes.reto.domain.entity.PersonEntity;
import com.clientes.reto.domain.entity.ProductEntity;
import com.clientes.reto.repository.PersonRepository;
import com.clientes.reto.repository.ProductRepository;
import com.clientes.reto.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public  PersonEntity save(PersonEntity personEntity) throws CustomResponse {
        if (personEntity.getAge() >= 18){
            return personRepository.save(personEntity);
        }
        else throw new CustomResponse("Debe ser mayor a 18 años");
    }
    public void delete(String email) throws CustomResponse {
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
        else throw new CustomResponse("se encontraron deudas en sus cuentas asociadas");
    }
}
