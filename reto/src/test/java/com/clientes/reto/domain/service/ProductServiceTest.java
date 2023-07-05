package com.clientes.reto.domain.service;
import static org.mockito.BDDMockito.given;

import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.domain.dto.ProductDto;
import com.clientes.reto.domain.repository.IPersonRepository;
import com.clientes.reto.domain.repository.IProdcutDtoRepository;
import com.clientes.reto.persistence.enums.AccountType;
import com.clientes.reto.persistence.enums.State;
import com.clientes.reto.utils.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    IProdcutDtoRepository prodcutDtoRepository;
    @Mock
    IPersonRepository personRepository;
    @InjectMocks
    ProductService productService;

    ProductDto productDto = new ProductDto();
    @BeforeEach
    void setUp(){
        productDto.setAccountType(AccountType.AHORROS);
        productDto.setDeudas(false);
        productDto.setProductNumber(1234567890);
        productDto.setCreationDate(new Date());
        productDto.setUpdateDate(new Date());
        productDto.setBalance(2000);
        productDto.setAvailableBalance(1500);
        productDto.setIdClient("juan@gmail.com");
    }

    @Test
    void finById() {
        given(prodcutDtoRepository.finById(productDto.getProductNumber())).willReturn(productDto);

        ProductDto productDto1 =productService.finById(productDto.getProductNumber());

        assertThat(productDto1).isNotNull();

    }

    @Test
    void save() {
    }

    @Test
    void create() {
        PersonDto personDto = new PersonDto();
        personDto.setIdNumber(12345678);
        personDto.setEmail(productDto.getIdClient());

        given(personRepository.finById(productDto.getIdClient())).willReturn(personDto);
        given(prodcutDtoRepository.save(productDto)).willReturn(productDto);
        ProductDto productDto1 = productService.create(productDto);
        assertThat(productDto1).isNotNull();
    }
    @Test
    void create_with_exception() {
        PersonDto personDto = new PersonDto();
        personDto.setIdNumber(12345678);
        personDto.setEmail(productDto.getIdClient());

        given(personRepository.finById(productDto.getIdClient())).willReturn(null);
        assertThrows(CustomException.class,()->{
            productService.create(productDto);
        });
        verify(prodcutDtoRepository,never()).save(productDto);
    }

    @Test
    void finByUser() {
        given(prodcutDtoRepository.finByUser(productDto.getIdClient())).willReturn(List.of(productDto));
        List<ProductDto> list = productService.finByUser(productDto.getIdClient());

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void inactive() {
        given(prodcutDtoRepository.finById(productDto.getProductNumber())).willReturn(productDto);
        given(prodcutDtoRepository.save(productDto)).willReturn(productDto);
        ProductDto productDto1 = productService.inactive(productDto.getProductNumber());
        assertThat(productDto1.getState()).isEqualTo(State.INACTIVO);
    }

    @Test
    void cancelar() {
        given(prodcutDtoRepository.finById(productDto.getProductNumber())).willReturn(productDto);
        given(prodcutDtoRepository.save(productDto)).willReturn(productDto);
        productDto.setBalance(0.1);
        ProductDto productDto1 = productService.cancelar(productDto.getProductNumber());
        assertThat(productDto1.getState()).isEqualTo(State.CANCELADA);
    }
    @Test
    void cancelar_with_exception() {
        given(prodcutDtoRepository.finById(productDto.getProductNumber())).willReturn(productDto);
        productDto.setBalance(2);
        assertThrows(CustomException.class,()->{
            productService.cancelar(productDto.getProductNumber());
        });

        verify(prodcutDtoRepository,never()).save(productDto);
    }

    @Test
    void getALl() {
        ProductDto productDto1 = new ProductDto();
        productDto1.setBalance(200);
        productDto1.setProductNumber(1234567891);
        productDto1.setIdClient("juanma@gmail.com");
        productDto1.setAvailableBalance(250);
        given(prodcutDtoRepository.getAll()).willReturn(List.of(productDto1,productDto));

        List<ProductDto> list = productService.getALl();

        assertThat(list.size()).isEqualTo(2);
    }
}