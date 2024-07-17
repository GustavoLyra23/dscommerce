package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDto;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductDto fidById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping
    public Page<ProductDto> fidAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

}
