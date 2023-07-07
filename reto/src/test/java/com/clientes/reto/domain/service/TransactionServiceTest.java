package com.clientes.reto.domain.service;
import static org.assertj.core.api.Assertions.as;
import static org.mockito.BDDMockito.given;

import com.clientes.reto.domain.dto.ProductDto;
import com.clientes.reto.domain.dto.TransactionDto;
import com.clientes.reto.domain.repository.ITransactionRepository;
import com.clientes.reto.domain.usecase.IProductUseCase;
import com.clientes.reto.persistence.enums.AccountType;
import com.clientes.reto.persistence.enums.TransactionsEnum;
import com.clientes.reto.utils.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @Mock
    IProductUseCase iProductUseCase;
    @Mock
    ITransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;

    TransactionDto transactionDto = new TransactionDto();
    ProductDto productDto = new ProductDto();
    @BeforeEach
    void setUp(){
        transactionDto.setMonto(10000);
        productDto.setBalance(1000);
        productDto.setAvailableBalance(500);
        productDto.setDeudas(false);
        productDto.setProductNumber("1234567890");
        productDto.setAccountType(AccountType.AHORROS);
        transactionDto.setAccountId(productDto.getProductNumber());
        transactionDto.setTransaccionType(TransactionsEnum.RETIRO);
    }

    @Test
    void retirar() {
        transactionDto.setMonto(200);
        given(iProductUseCase.finById(transactionDto.getAccountId())).willReturn(productDto);
        given(transactionRepository.save(transactionDto)).willReturn(transactionDto);

        TransactionDto transactionDto1 = transactionService.retirar(transactionDto);

        assertThat(transactionDto1).isNotNull();
        assertThat(productDto.getBalance()).isEqualTo(800);
        assertThat(productDto.getAvailableBalance()).isEqualTo(300);
    }
    @Test
    void retirar_with_exepttion() {
        transactionDto.setMonto(1100);
        given(iProductUseCase.finById(transactionDto.getAccountId())).willReturn(productDto);
        assertThrows(CustomException.class,()->{
            transactionService.retirar(transactionDto);
        });

        verify(transactionRepository,never()).save(transactionDto);
        assertThat(productDto.getBalance()).isEqualTo(1000);
        assertThat(productDto.getAvailableBalance()).isEqualTo(500);
    }
    @Test
    void retirar_with_corriente() {
        productDto.setAccountType(AccountType.CORRIENTE);
        transactionDto.setMonto(1100);
        given(iProductUseCase.finById(transactionDto.getAccountId())).willReturn(productDto);
        given(transactionRepository.save(transactionDto)).willReturn(transactionDto);

        TransactionDto transactionDto1 = transactionService.retirar(transactionDto);

        assertThat(transactionDto1).isNotNull();
        assertThat(productDto.isDeudas()).isEqualTo(true);
        assertThat(productDto.getBalance()).isEqualTo(-100);
        assertThat(productDto.getAvailableBalance()).isEqualTo(-600);

    }
    @Test
    void retirar_with_corriente_exception() {
        productDto.setAccountType(AccountType.CORRIENTE);
        transactionDto.setMonto(3100000);
        given(iProductUseCase.finById(transactionDto.getAccountId())).willReturn(productDto);
        assertThrows(CustomException.class,()->{
            transactionService.retirar(transactionDto);
        });

        verify(transactionRepository,never()).save(transactionDto);
        assertThat(productDto.getBalance()).isEqualTo(1000);
        assertThat(productDto.getAvailableBalance()).isEqualTo(500);
    }


    @Test
    void consignar() {
        transactionDto.setTransaccionType(TransactionsEnum.CONSIGNACION);
        given(iProductUseCase.finById(transactionDto.getAccountId())).willReturn(productDto);
        given(transactionRepository.save(transactionDto)).willReturn(transactionDto);

        TransactionDto transactionDto1 = transactionService.consignar(transactionDto);

        assertThat(transactionDto1).isNotNull();
        assertThat(productDto.getBalance()).isEqualTo(11000);
        assertThat(productDto.getAvailableBalance()).isEqualTo(10500);
    }
    @Test
    void consignar_with_exception() {
        transactionDto.setTransaccionType(TransactionsEnum.CONSIGNACION);
        given(iProductUseCase.finById(transactionDto.getAccountId())).willReturn(null);

        assertThrows(CustomException.class,()->{
            transactionService.consignar(transactionDto);
        });

        verify(transactionRepository,never()).save(transactionDto);
        assertThat(productDto.getBalance()).isEqualTo(1000);
        assertThat(productDto.getAvailableBalance()).isEqualTo(500);

    }

    @Test
    void doATransference() {
        ProductDto productDto1 = new ProductDto();
        productDto1.setAccountType(AccountType.AHORROS);
        productDto1.setProductNumber("1234567893");
        productDto1.setBalance(1000);
        productDto1.setAvailableBalance(500);
        transactionDto.setTransaccionType(TransactionsEnum.TRANSFERENCIA);
        transactionDto.setMonto(200);
        given(iProductUseCase.finById(productDto.getProductNumber())).willReturn(productDto);
        given(iProductUseCase.finById(productDto1.getProductNumber())).willReturn(productDto1);
        given(transactionRepository.save(transactionDto)).willReturn(transactionDto);

        TransactionDto transactionDto1 = transactionService.doATransference(productDto1.getProductNumber(),transactionDto);

        assertThat(transactionDto1).isNotNull();
        assertThat(productDto1.getBalance()).isEqualTo(1200);
        assertThat(productDto1.getAvailableBalance()).isEqualTo(700);
        assertThat(productDto.getBalance()).isEqualTo(800);
        assertThat(productDto.getAvailableBalance()).isEqualTo(300);
    }

    @Test
    void doATransference_with_corriente() {
        productDto.setAccountType(AccountType.CORRIENTE);
        ProductDto productDto1 = new ProductDto();
        productDto1.setAccountType(AccountType.AHORROS);
        productDto1.setProductNumber("1234567893");
        productDto1.setBalance(1000);
        productDto1.setAvailableBalance(500);
        transactionDto.setTransaccionType(TransactionsEnum.TRANSFERENCIA);
        transactionDto.setMonto(10000);
        given(iProductUseCase.finById(productDto.getProductNumber())).willReturn(productDto);
        given(iProductUseCase.finById(productDto1.getProductNumber())).willReturn(productDto1);
        given(transactionRepository.save(transactionDto)).willReturn(transactionDto);

        TransactionDto transactionDto1 = transactionService.doATransference(productDto1.getProductNumber(),transactionDto);

        assertThat(transactionDto1).isNotNull();
        assertThat(productDto1.getBalance()).isEqualTo(11000);
        assertThat(productDto1.getAvailableBalance()).isEqualTo(10500);
        assertThat(productDto.getBalance()).isEqualTo(-9000);
        assertThat(productDto.getAvailableBalance()).isEqualTo(-9500);
    }
    @Test
    void doATransference_with_exception() {
        ProductDto productDto1 = new ProductDto();
        productDto1.setAccountType(AccountType.AHORROS);
        productDto1.setProductNumber("1234567893");
        productDto1.setBalance(1000);
        productDto1.setAvailableBalance(500);
        transactionDto.setTransaccionType(TransactionsEnum.TRANSFERENCIA);
        transactionDto.setMonto(1100);
        given(iProductUseCase.finById(productDto.getProductNumber())).willReturn(productDto);
        given(iProductUseCase.finById(productDto1.getProductNumber())).willReturn(productDto1);

        assertThrows(CustomException.class,()->{
            transactionService.doATransference(productDto1.getProductNumber(),transactionDto);
        });

        verify(transactionRepository,never()).save(transactionDto);
        assertThat(productDto.getBalance()).isEqualTo(1000);
        assertThat(productDto.getAvailableBalance()).isEqualTo(500);
        assertThat(productDto1.getBalance()).isEqualTo(1000);
        assertThat(productDto1.getAvailableBalance()).isEqualTo(500);
    }

    @Test
    void doATransference_with_corriente_with_exception() {
        productDto.setAccountType(AccountType.CORRIENTE);
        ProductDto productDto1 = new ProductDto();
        productDto1.setAccountType(AccountType.AHORROS);
        productDto1.setProductNumber("1234567893");
        productDto1.setBalance(1000);
        productDto1.setAvailableBalance(500);
        transactionDto.setTransaccionType(TransactionsEnum.TRANSFERENCIA);
        transactionDto.setMonto(3100000);
        given(iProductUseCase.finById(productDto.getProductNumber())).willReturn(productDto);
        given(iProductUseCase.finById(productDto1.getProductNumber())).willReturn(productDto1);
        assertThrows(CustomException.class,()->{
            transactionService.doATransference(productDto1.getProductNumber(),transactionDto);
        });

        verify(transactionRepository,never()).save(transactionDto);
        assertThat(productDto.getBalance()).isEqualTo(1000);
        assertThat(productDto.getAvailableBalance()).isEqualTo(500);
        assertThat(productDto1.getBalance()).isEqualTo(1000);
        assertThat(productDto1.getAvailableBalance()).isEqualTo(500);

    }

    @Test
    void findByAccountId() {
        TransactionDto transactionDto1 = new TransactionDto();
        transactionDto1.setMonto(3000);
        transactionDto1.setAccountId(productDto.getProductNumber());
        transactionDto1.setTransaccionType(TransactionsEnum.CONSIGNACION);

        given(transactionRepository.findByAccountId(productDto.getProductNumber())).willReturn(List.of(transactionDto1,transactionDto));

        List<TransactionDto> list = transactionService.findByAccountId(productDto.getProductNumber());

        assertThat(list.size()).isEqualTo(2);
    }
}