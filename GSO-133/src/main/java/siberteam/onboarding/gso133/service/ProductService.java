package siberteam.onboarding.gso133.service;

import org.springframework.stereotype.Service;
import siberteam.onboarding.gso133.data.dto.ProductDto;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    public List<ProductDto> getProducts() {
        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto());
        return products;
    }
}
