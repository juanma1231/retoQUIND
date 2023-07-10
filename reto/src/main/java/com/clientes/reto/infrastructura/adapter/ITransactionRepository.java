package com.clientes.reto.infrastructura.adapter;

import com.clientes.reto.domain.dto.TransactionDto;

import java.util.List;

public interface ITransactionRepository {
    List<TransactionDto> findByAccountId(String accountId);
    TransactionDto save(TransactionDto transactionDto);
}
