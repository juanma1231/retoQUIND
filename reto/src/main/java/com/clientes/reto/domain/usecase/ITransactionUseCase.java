package com.clientes.reto.domain.usecase;

import com.clientes.reto.domain.dto.TransactionDto;

import java.util.List;

public interface ITransactionUseCase {
    TransactionDto retirar(TransactionDto transactionDto);
    TransactionDto consignar(TransactionDto transactionDto);
    TransactionDto doATransference(Integer receptor, TransactionDto transactionDto);
    List<TransactionDto> findByAccountId(Integer accountId);
}
