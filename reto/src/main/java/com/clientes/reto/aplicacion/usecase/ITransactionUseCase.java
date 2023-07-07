package com.clientes.reto.aplicacion.usecase;

import com.clientes.reto.domain.dto.TransactionDto;

import java.util.List;

public interface ITransactionUseCase {
    TransactionDto retirar(TransactionDto transactionDto);
    TransactionDto consignar(TransactionDto transactionDto);
    TransactionDto doATransference(String receptor, TransactionDto transactionDto);
    List<TransactionDto> findByAccountId(String accountId);
}
