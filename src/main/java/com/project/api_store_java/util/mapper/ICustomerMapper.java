package com.project.api_store_java.util.mapper;

import com.project.api_store_java.dto.CustomerDTO;
import com.project.api_store_java.domain.model.Customer;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ICustomerMapper extends Converter<Customer, CustomerDTO> {

    @Override
    CustomerDTO convert(Customer resource);
}
