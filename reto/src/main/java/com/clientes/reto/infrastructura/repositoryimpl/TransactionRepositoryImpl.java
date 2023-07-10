package com.clientes.reto.infrastructura.repositoryimpl;

import com.clientes.reto.domain.dto.TransactionDto;
import com.clientes.reto.domain.adapter.ITransactionRepository;
import com.clientes.reto.infrastructura.entity.TransactionEntity;
import com.clientes.reto.infrastructura.mappers.TransactionMapper;
import com.clientes.reto.infrastructura.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TransactionRepositoryImpl implements ITransactionRepository {
    @Autowired
    @Lazy
    private TransactionRepository repository;

    @Autowired
    private TransactionMapper mapper;
    @Override
    public List<TransactionDto> findByAccountId(String accountId) {
        return mapper.toTransactions(repository.findByAccountId(accountId));
    }

    @Override
    public TransactionDto save(TransactionDto transactionDto) {
        TransactionEntity transactionEntity = mapper.toTransactionEntity(transactionDto);
        return mapper.toTransactionDto(repository.save(transactionEntity));
    }
}
