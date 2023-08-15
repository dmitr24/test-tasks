package siberteam.onboarding.gso121.store.service;

import lombok.RequiredArgsConstructor;
import siberteam.onboarding.gso121.store.dao.ProductDao;
import siberteam.onboarding.gso121.store.data.dto.CreateUpdateProductDto;
import siberteam.onboarding.gso121.store.data.dto.ProductDto;
import siberteam.onboarding.gso121.store.data.mapper.ProductMapper;
import siberteam.onboarding.gso121.store.domain.ProductEntity;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final ProductMapper productMapper;

    public void create(CreateUpdateProductDto createProductDto) {
        ProductEntity productEntity = productMapper.toEntity(createProductDto);
        productDao.create(productEntity);
    }

    public ProductDto getByCode(int code) {
        ProductEntity productEntity = productDao.getByCode(code);
        return productMapper.toDto(productEntity);
    }

    public Collection<ProductDto> getAll() {
        return productDao
                .getAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public void update(int code, CreateUpdateProductDto updateProductDto) {
        ProductEntity productEntity = productMapper.toEntity(updateProductDto);
        productDao.update(code, productEntity);
    }

    public void delete(int code) {
        productDao.delete(code);
    }
}
