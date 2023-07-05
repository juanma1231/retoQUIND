package com.clientes.reto.domain.service;

import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.domain.repository.IPersonRepository;
import com.clientes.reto.domain.usecase.IPersonUseCase;
import com.clientes.reto.utils.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    @Autowired
    IPersonUseCase iPersonUseCase;
    @Test
    void getAllUsers() {
    }

    @Test
    void create() {


        PersonDto personDto = new PersonDto();
        personDto.setAge(19);
        personDto.setEmail("juan@gmail.com");
        personDto.setName("juan manuel");
        Mockito.when(iPersonUseCase.findById(Mockito.anyString())).thenReturn(null);
        System.out.println(iPersonUseCase.create(personDto));
        Assertions.assertEquals(PersonDto.class,iPersonUseCase.create(personDto));
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }

    @Test
    void patch() {
    }

    @Test
    void save() {
    }
}