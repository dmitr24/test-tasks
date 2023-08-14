package siberteam.onboarding.gso121.store.data.mapper;

import siberteam.onboarding.gso121.store.data.dto.CreateProductDto;
import siberteam.onboarding.gso121.store.data.dto.ProductDto;
import siberteam.onboarding.gso121.store.domain.ProductEntity;

public class ProductMapper {
    public ProductEntity toEntity(CreateProductDto dto) {
        ProductEntity entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public ProductDto toDto(ProductEntity entity) {
        ProductDto dto = new ProductDto();
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
