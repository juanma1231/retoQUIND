package com.clientes.reto.aplicacion.service;

import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.domain.dto.ProductDto;
import com.clientes.reto.infrastructura.adapter.IPersonRepository;
import com.clientes.reto.infrastructura.adapter.IProdcutDtoRepository;
import com.clientes.reto.domain.usecase.IPersonUseCase;
import com.clientes.reto.utils.CustomException;
import com.clientes.reto.utils.Fecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.clientes.reto.utils.UtilString;

import java.util.Date;
import java.util.List;


@Service
public class PersonService implements IPersonUseCase {
    @Autowired
    @Lazy
    IProdcutDtoRepository productService;
    @Autowired
    @Lazy
    IPersonRepository personRepository;


    public Iterable<PersonDto> getAllUsers(){
        return personRepository.getAllUsers();
    }

    public  PersonDto create(PersonDto personDto){
        PersonDto person = personRepository.finById(personDto.getEmail());
        Date fechaActual = new Date();

        personDto.setAge(Fecha.calcularEdad(personDto.getBirthDay()));

        if(person==null){
            if (personDto.getAge() >= 18){
                personDto.setCreationDate(fechaActual);
                personDto.setUpdateDate(fechaActual);
                return personRepository.save(personDto);
            }
            else throw new CustomException("Debe ser mayor a 18 años");
        }else throw new CustomException("El usuario ya existe");

    }
    public void delete(String email){
        List<ProductDto> products = productService.finByUser(email);
        boolean deudas= false;
        for(ProductDto product:products){
            if (product.isDeudas()){
                deudas= true;
            }
        }
        if(!deudas){
            personRepository.delete(email);
        }
        else throw new CustomException("se encontraron deudas en sus cuentas asociadas");
    }
    public  PersonDto findById(String email){
        return personRepository.finById(email);
    }
    public PersonDto patch(String email, PersonDto person){
        PersonDto person1 = personRepository.finById(email);
        person1.setName(UtilString.isEmptyOrNull(person.getName()) ? person1.getName() : person.getName());
        person1.setLastName(UtilString.isEmptyOrNull(person.getLastName()) ? person1.getLastName() : person.getLastName());
        person1.setIdType(UtilString.isEmptyOrNull(person.getIdType()) ? person1.getIdType() : person.getIdType());
        person1.setIdNumber(UtilString.isEmptyOrNull(person.getIdNumber()) ? person1.getIdNumber() : person.getIdNumber());
        Date fechaActual = new Date();
        person1.setUpdateDate(fechaActual);
        return personRepository.save(person1);
    }
    public PersonDto save(PersonDto person){

        return personRepository.save(person);
    }
}
