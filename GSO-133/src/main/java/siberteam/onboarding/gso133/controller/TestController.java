package siberteam.onboarding.gso133.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import siberteam.onboarding.gso133.data.dto.CreateUpdateProductDto;
import siberteam.onboarding.gso133.service.ProductService;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class TestController {
    private final ProductService productService;

    @GetMapping
    public String getAll() {
        return productService.getProducts().toString();
    }

    @PostMapping
    public void save(CreateUpdateProductDto createUpdateProductDto) {

    }

    @PutMapping
    public void update(Integer code, CreateUpdateProductDto createUpdateProductDto) {

    }

    @DeleteMapping
    public void delete(Integer code) {

    }
}
