package siberteam.onboarding.gso133.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siberteam.onboarding.gso133.service.ProductService;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class TestController {
    private final ProductService productService;

    @GetMapping
    public String hello() {
        return productService.getProducts().toString();
    }
}
