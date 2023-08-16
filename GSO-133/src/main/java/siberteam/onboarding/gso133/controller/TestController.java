package siberteam.onboarding.gso133.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import siberteam.onboarding.gso133.data.dto.CreateUpdateProductDto;
import siberteam.onboarding.gso133.data.dto.ProductDto;
import siberteam.onboarding.gso133.service.ProductService;
import java.util.List;

@Validated
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class TestController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody @Valid CreateUpdateProductDto createUpdateProductDto) {
        return productService.create(createUpdateProductDto);
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @PutMapping("/{code}")
    public ProductDto update(@PathVariable @Min(value = 1, message = "code is required and can't be less then 1") int code,
                       @RequestBody @Valid CreateUpdateProductDto createUpdateProductDto) {
        return productService.update(code, createUpdateProductDto);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable @Min(value = 1, message = "code is required and can't be less then 1") int code) {
        productService.delete(code);
    }
}
