package com.store.api.config;

import com.store.api.util.mappers.ICustomerMapper;
import com.store.api.util.mappers.IProductMapper;
import com.store.api.util.mappers.ISaleMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MapperConfig {

    @Bean
    public ICustomerMapper customerMapper() {
        return ICustomerMapper.INSTANCE;
    }

    @Bean
    public ISaleMapper saleMapper() {
        return ISaleMapper.INSTANCE;
    }

    @Bean
    public IProductMapper productMapper() {
        return IProductMapper.INSTANCE;
    }
}
