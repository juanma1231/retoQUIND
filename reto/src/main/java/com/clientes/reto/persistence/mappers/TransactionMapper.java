package com.clientes.reto.persistence.mappers;

import com.clientes.reto.domain.dto.TransactionDto;
import com.clientes.reto.persistence.entity.TransactionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mappings({
            @Mapping(source ="id" , target ="id" ),
            @Mapping(source ="transactionType" , target ="transaccionType"),
            @Mapping(source ="description" , target ="description"),
            @Mapping(source ="monto" , target ="monto" )
    })
    TransactionDto toTransactionDto(TransactionEntity transactionEntity);

    List<TransactionDto> toTransactions(List<TransactionEntity> transactionEntities);

    @InheritInverseConfiguration
    @Mapping(target = "productEntity", ignore = true)
    TransactionEntity toTransactionEntity(TransactionDto transactionDto);


}
