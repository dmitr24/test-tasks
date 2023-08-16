package siberteam.onboarding.gso133.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import siberteam.onboarding.gso133.data.dto.CreateUpdateProductDto;
import siberteam.onboarding.gso133.data.dto.ProductDto;
import siberteam.onboarding.gso133.data.mapper.ProductMapper;
import siberteam.onboarding.gso133.domain.ProductEntity;
import siberteam.onboarding.gso133.exception.ProductNotFoundException;
import siberteam.onboarding.gso133.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<ProductDto> getProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDto create(CreateUpdateProductDto createUpdateProductDto) {
        ProductEntity productEntity = productMapper.toEntity(createUpdateProductDto);
        productEntity = productRepository.save(productEntity);
        return productMapper.toDto(productEntity);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ProductDto update(int code, CreateUpdateProductDto createUpdateProductDto) {
        ProductEntity productEntity = productRepository
                .findById(code)
                .orElseThrow(() -> new ProductNotFoundException("Product with such code not exist - " + code));
        productEntity.setName(createUpdateProductDto.getName());
        productEntity.setPrice(createUpdateProductDto.getPrice());
        return productMapper.toDto(productEntity);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(int code) {
        ProductEntity productEntity = productRepository
                .findById(code)
                .orElseThrow(() -> new ProductNotFoundException("Product with such code not exist - " + code));
        productRepository.delete(productEntity);
    }
}
