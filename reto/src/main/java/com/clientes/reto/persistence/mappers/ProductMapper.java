package com.clientes.reto.persistence.mappers;

import com.clientes.reto.domain.dto.ProductDto;
import com.clientes.reto.persistence.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mappings({
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

    })
    ProductDto toProductDto(ProductEntity productEntity);

    List<ProductDto> toProducts(List<ProductEntity> productEntities);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "transactions", ignore = true),
            @Mapping(target = "client", ignore = true)
    })
    ProductEntity toProductEntity(ProductDto productDto);
}
