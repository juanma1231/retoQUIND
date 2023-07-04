package com.clientes.reto.domain.mappers;

import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.domain.dto.ProductDto;
import com.clientes.reto.domain.dto.TransactionDto;
import com.clientes.reto.domain.entity.PersonEntity;
import com.clientes.reto.domain.entity.ProductEntity;
import com.clientes.reto.domain.entity.TransactionEntity;
import com.clientes.reto.domain.enums.State;
import com.clientes.reto.domain.enums.TransactionsEnum;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-29T15:51:25-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDto toTransactionDto(TransactionEntity transactionEntity) {
        if ( transactionEntity == null ) {
            return null;
        }

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setId( transactionEntity.getId() );
        transactionDto.setTransaccionType(transactionEntity.setTransaccionType );
        transactionDto.setDescription( transactionEntity.getDescription() );
        transactionDto.setMonto( transactionEntity.getMonto() );
        transactionDto.setProductDto( productEntityToProductDto( transactionEntity.getProductEntity() ) );

        return transactionDto;
    }

    @Override
    public TransactionEntity toTransactionEntity(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setId( transactionDto.getId() );
        transactionEntity.setTransactionType( Enum.valueOf( TransactionsEnum.class, transactionDto.getTransaccionType() ) );
        transactionEntity.setDescription( transactionDto.getDescription() );
        transactionEntity.setMonto( transactionDto.getMonto() );
        transactionEntity.setProductEntity( productDtoToProductEntity( transactionDto.getProductDto() ) );

        return transactionEntity;
    }

    protected List<TransactionDto> transactionEntityListToTransactionDtoList(List<TransactionEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<TransactionDto> list1 = new ArrayList<TransactionDto>( list.size() );
        for ( TransactionEntity transactionEntity : list ) {
            list1.add( toTransactionDto( transactionEntity ) );
        }

        return list1;
    }

    protected PersonDto personEntityToPersonDto(PersonEntity personEntity) {
        if ( personEntity == null ) {
            return null;
        }

        PersonDto personDto = new PersonDto();

        personDto.setIdType( personEntity.getIdType() );
        personDto.setIdNumber( personEntity.getIdNumber() );
        personDto.setName( personEntity.getName() );
        personDto.setEmail( personEntity.getEmail() );
        personDto.setBirthDay( personEntity.getBirthDay() );
        personDto.setAge( personEntity.getAge() );
        personDto.setCreationDate( personEntity.getCreationDate() );
        personDto.setUpdateDate( personEntity.getUpdateDate() );

        return personDto;
    }

    protected ProductDto productEntityToProductDto(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setAccountType( productEntity.getAccountType() );
        productDto.setState( productEntity.getState().name() );
        productDto.setBalance( productEntity.getBalance() );
        productDto.setAvailableBalance( productEntity.getAvailableBalance() );
        productDto.setExcentaGMF( productEntity.getExcentaGMF() );
        productDto.setCreationDate( productEntity.getCreationDate() );
        productDto.setUpdateDate( productEntity.getUpdateDate() );
        productDto.setIdClient( productEntity.getIdClient() );
        productDto.setTransactions( transactionEntityListToTransactionDtoList( productEntity.getTransactions() ) );
        productDto.setClient( personEntityToPersonDto( productEntity.getClient() ) );

        return productDto;
    }

    protected List<TransactionEntity> transactionDtoListToTransactionEntityList(List<TransactionDto> list) {
        if ( list == null ) {
            return null;
        }

        List<TransactionEntity> list1 = new ArrayList<TransactionEntity>( list.size() );
        for ( TransactionDto transactionDto : list ) {
            list1.add( toTransactionEntity( transactionDto ) );
        }

        return list1;
    }

    protected PersonEntity personDtoToPersonEntity(PersonDto personDto) {
        if ( personDto == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setIdType( personDto.getIdType() );
        personEntity.setIdNumber( personDto.getIdNumber() );
        personEntity.setName( personDto.getName() );
        personEntity.setEmail( personDto.getEmail() );
        personEntity.setBirthDay( personDto.getBirthDay() );
        personEntity.setAge( personDto.getAge() );
        personEntity.setCreationDate( personDto.getCreationDate() );
        personEntity.setUpdateDate( personDto.getUpdateDate() );

        return personEntity;
    }

    protected ProductEntity productDtoToProductEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setAccountType( productDto.getAccountType() );
        productEntity.setState(productDto.getState() );
        productEntity.setBalance( productDto.getBalance() );
        productEntity.setAvailableBalance( productDto.getAvailableBalance() );
        productEntity.setExcentaGMF( productDto.isExcentaGMF() );
        productEntity.setCreationDate( productDto.getCreationDate() );
        productEntity.setUpdateDate( productDto.getUpdateDate() );
        productEntity.setIdClient( productDto.getIdClient() );
        productEntity.setTransactions( transactionDtoListToTransactionEntityList( productDto.getTransactions() ) );
        productEntity.setClient( personDtoToPersonEntity( productDto.getClient() ) );

        return productEntity;
    }
}
