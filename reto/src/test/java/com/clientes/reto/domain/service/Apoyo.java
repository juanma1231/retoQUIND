package com.clientes.reto.domain.service;

import static org.mockito.BDDMockito.given;

import com.clientes.reto.aplicacion.service.PersonService;
import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.domain.adapter.IPersonRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class Apoyo {
    @MockBean
    private IPersonRepository iPersonRepository;

    @Autowired
    private PersonService personService;
    @BeforeEach
    void setUp(){
        iPersonRepository = mock(IPersonRepository.class);
        personService = new PersonService();
    }

    @Test
    void getAllUsers() {
        System.out.println("hola");
    }

    @Test
    void create() {
        PersonDto personDto = new PersonDto();
        personDto.setName("Juan manuel");
        personDto.setAge(19);
        personDto.setEmail("Juan Mnauel");
        Date fechaNacimiento = new Date(99, 4, 8);
        personDto.setBirthDay(fechaNacimiento);
        personDto.setCreationDate(new Date());
        personDto.setIdType("CEDULA");
        personDto.setUpdateDate(new Date());
        personDto.setIdNumber(1234567890);
        when(iPersonRepository.save(personDto)).thenReturn(personDto);
        when(iPersonRepository.finById(personDto.getEmail())).thenReturn(null);
        given(iPersonRepository.finById(personDto.getEmail())).willReturn(null);
        given(iPersonRepository.save(personDto)).willReturn(personDto);

        PersonDto personaGuardada = personService.create(personDto);

        assertThat(personaGuardada).isNotNull();


    }
}