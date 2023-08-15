package siberteam.onboarding.gso133.data.mapper;

import org.springframework.stereotype.Component;
import siberteam.onboarding.gso133.data.dto.CreateUpdateProductDto;
import siberteam.onboarding.gso133.data.dto.ProductDto;
import siberteam.onboarding.gso133.domain.ProductEntity;

@Component
public class ProductMapper {
    public ProductEntity toEntity(CreateUpdateProductDto dto) {
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
