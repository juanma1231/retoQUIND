package com.clientes.reto.persistence.mappers;

import com.clientes.reto.domain.dto.PersonDto;
import com.clientes.reto.persistence.entity.PersonEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mappings({
            @Mapping(source ="idType", target ="idType" ),
            @Mapping(source ="name" , target ="name" ),
            @Mapping(source ="lastname" , target ="lastName" ),
            @Mapping(source ="email" , target ="email"),
            @Mapping(source ="birthDay" , target ="birthDay"),
            @Mapping(source ="age" , target ="age"),
            @Mapping(source ="creationDate" , target ="creationDate" ),
            @Mapping(source ="updateDate" , target ="updateDate" ),
            @Mapping(source ="idNumber" , target ="idNumber")
    })
    PersonDto toPersonDto(PersonEntity personEntity);

    List<PersonDto> toPeople(List<PersonEntity> personEntities);

    @InheritInverseConfiguration
    PersonEntity toPersonEntity(PersonDto personDto);


}
