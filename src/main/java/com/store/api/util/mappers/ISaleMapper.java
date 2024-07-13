package com.store.api.util.mappers;

import com.store.api.dto.SaleDTO;
import com.store.api.persistence.model.entities.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { ICustomerMapper.class, IProductMapper.class})
public interface ISaleMapper {

    ISaleMapper INSTANCE = Mappers.getMapper(ISaleMapper.class);

    @Mappings({
            @Mapping(source = "productList",target = "productListDTO"),
            @Mapping(source = "customer", target = "customerDTO")
    })
    SaleDTO saleToSaleDTO(Sale sale);

    @Mappings({
            @Mapping(source = "productListDTO",target = "productList"),
            @Mapping(source = "customer", target = "customer")
    })
    Sale saleDTOToSale(SaleDTO saleDTO);
}
