package com.clientes.reto.domain.mappers;

import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.domain.entity.PersonEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-29T15:51:25-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonDto toPersonDto(PersonEntity personEntity) {
        if ( personEntity == null ) {
            return null;
        }

        PersonDto personDto = new PersonDto();

        personDto.setIdType( personEntity.getIdType() );
        personDto.setName( personEntity.getName() );
        personDto.setLastName( personEntity.getLastname() );
        personDto.setEmail( personEntity.getEmail() );
        personDto.setBirthDay( personEntity.getBirthDay() );
        personDto.setAge( personEntity.getAge() );
        personDto.setCreationDate( personEntity.getCreationDate() );
        personDto.setUpdateDate( personEntity.getUpdateDate() );
        personDto.setIdNumber( personEntity.getIdNumber() );

        return personDto;
    }

    @Override
    public PersonEntity toPersonEntity(PersonDto personDto) {
        if ( personDto == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setIdType( personDto.getIdType() );
        personEntity.setName( personDto.getName() );
        personEntity.setLastname( personDto.getLastName() );
        personEntity.setEmail( personDto.getEmail() );
        personEntity.setBirthDay( personDto.getBirthDay() );
        personEntity.setAge( personDto.getAge() );
        personEntity.setCreationDate( personDto.getCreationDate() );
        personEntity.setUpdateDate( personDto.getUpdateDate() );
        personEntity.setIdNumber( personDto.getIdNumber() );

        return personEntity;
    }
}
