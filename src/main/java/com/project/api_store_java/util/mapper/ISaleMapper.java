package com.project.api_store_java.util.mapper;

import com.project.api_store_java.domain.model.Sale;
import com.project.api_store_java.dto.SaleDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ISaleMapper extends Converter<Sale, SaleDTO> {

    @Override
    SaleDTO convert(Sale resource);
}
