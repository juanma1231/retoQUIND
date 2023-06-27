package com.clientes.reto.domain.mappers;

import com.clientes.reto.domain.dto.TransactionDto;
import com.clientes.reto.domain.entity.TransactionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mappings({
            @Mapping(source ="id" , target ="id" ),
            @Mapping(source ="transactionType" , target ="transaccionType"),
            @Mapping(source ="description" , target ="description"),
            @Mapping(source ="monto" , target ="monto" ),
            @Mapping(source ="productEntity" , target ="productDto")
    })
    TransactionDto toTransactionDto(TransactionEntity transactionEntity);

    @InheritInverseConfiguration
    TransactionEntity toTransactionEntity(TransactionDto transactionDto);


}
