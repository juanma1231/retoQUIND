package com.clientes.reto.domain.service;
import static org.mockito.BDDMockito.given;

import com.clientes.reto.aplicacion.service.PersonService;
import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.domain.dto.ProductDto;
import com.clientes.reto.domain.adapter.IPersonRepository;
import com.clientes.reto.domain.adapter.IProdcutDtoRepository;
import com.clientes.reto.infrastructura.enums.AccountType;
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
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private IPersonRepository iPersonRepository;
    @Mock
    IProdcutDtoRepository productService;
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
        ProductDto productDto = new ProductDto();
        productDto.setAccountType(AccountType.AHORROS);
        productDto.setDeudas(false);
        productDto.setProductNumber("1234567890");
        productDto.setCreationDate(new Date());
        productDto.setUpdateDate(new Date());
        productDto.setBalance(2000);
        productDto.setAvailableBalance(1500);
        String email = "Juan Mnauel";
        willDoNothing().given(iPersonRepository).delete(email);
        given(productService.finByUser(personDto.getEmail())).willReturn(List.of(productDto));
        //List<ProductDto> list = productService.finByUser(personDto.getEmail());
        //System.out.println("dandole");
        //System.out.println(list.size());
        personService.delete(email);
        verify(iPersonRepository,times(1)).delete(email);

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