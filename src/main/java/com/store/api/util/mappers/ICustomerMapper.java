package com.store.api.util.mappers;

import com.store.api.dto.CustomerDTO;
import com.store.api.persistence.model.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { ISaleMapper.class })
public interface ICustomerMapper {

    ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

    @Mappings({
            @Mapping(source = "roles",target = "rolesDTO"),
            @Mapping(source = "sale", target = "saleDTO")
    })
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mappings({
            @Mapping(source = "rolesDTO", target = "roles"),
            @Mapping(source = "saleDTO", target = "sale")
    })
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

}
