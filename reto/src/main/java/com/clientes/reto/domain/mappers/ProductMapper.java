package com.clientes.reto.domain.mappers;

import com.clientes.reto.domain.dto.ProductDto;
import com.clientes.reto.domain.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "accountType",target = "accountType"),
            @Mapping(source = "producNumber",target = "productNumber"),
            @Mapping(source = "state",target = "state"),
            @Mapping(source = "balance",target = "balance"),
            @Mapping(source = "deaudas",target = "deudas"),
            @Mapping(source = "availableBalance",target = "availableBalance"),
            @Mapping(source = "excentaGMF",target = "excentaGMF"),
            @Mapping(source = "creationDate",target = "creationDate"),
            @Mapping(source = "updateDate",target = "updateDate"),
            @Mapping(source = "idClient",target = "idClient"),
            @Mapping(source = "transactions",target = "transactions"),
            @Mapping(source = "client",target = "client")
    })
    ProductDto toProductDto(ProductEntity productEntity);

    @InheritInverseConfiguration
    ProductEntity toProductEntity(ProductDto productDto);
}
