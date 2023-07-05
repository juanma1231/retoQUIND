package com.clientes.reto.domain.service;
import static org.mockito.BDDMockito.given;
import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.domain.repository.IPersonRepository;
import com.clientes.reto.utils.CustomException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private IPersonRepository iPersonRepository;

    @InjectMocks
    private PersonService personService;
    PersonDto personDto = new PersonDto();
    @BeforeEach
    void setUp(){

        personDto.setName("Juan manuel");
        personDto.setAge(19);
        personDto.setEmail("Juan Mnauel");
        Date fechaNacimiento = new Date(99, 4, 8);
        personDto.setBirthDay(fechaNacimiento);
        personDto.setCreationDate(new Date());
        personDto.setIdType("CEDULA");
        personDto.setUpdateDate(new Date());
        personDto.setIdNumber(1234567890);

    }

    @Test
    void getAllUsers() {
        PersonDto personDto1 = new PersonDto();
        personDto1.setName("Juan manuel");
        personDto1.setAge(19);
        personDto1.setEmail("Juan@gmail.com");
        Date fechaNacimiento = new Date(99, 4, 8);
        personDto1.setBirthDay(fechaNacimiento);
        personDto1.setCreationDate(new Date());
        personDto1.setIdType("CEDULA");
        personDto1.setUpdateDate(new Date());
        personDto1.setIdNumber(1234567890);
        given(iPersonRepository.getAllUsers()).willReturn(List.of(personDto,personDto1));
        List<PersonDto> list = (List<PersonDto>) personService.getAllUsers();
        assertThat(list.size()).isEqualTo(2);


    }

    @Test
    void create() {
        //given
        given(iPersonRepository.finById(personDto.getEmail())).willReturn(null);
        given(iPersonRepository.save(personDto)).willReturn(personDto);
        //when
        PersonDto personaGuardada = personService.create(personDto);
        //then
        assertThat(personaGuardada).isNotNull();
    }
    @Test
    void creste_with_exeption(){
        given(iPersonRepository.finById(personDto.getEmail())).willReturn(personDto);
        assertThrows(CustomException.class,()->{
            personService.create(personDto);
        });

        verify(iPersonRepository,never()).save(personDto);
    }

    @Test
    void delete() {

    }

    @Test
    void findById() {
        given(iPersonRepository.finById(personDto.getEmail())).willReturn(personDto);
        PersonDto personDto1 = personService.findById(personDto.getEmail());
        assertThat(personDto1).isNotNull();

    }

    @Test
    void patch() {
    }

    @Test
    void save() {
    }
}