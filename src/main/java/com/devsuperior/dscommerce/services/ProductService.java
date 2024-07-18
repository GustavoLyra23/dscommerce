package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDto;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Product result = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return new ProductDto(result);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductDto::new);
    }

    @Transactional()
    public ProductDto insert(ProductDto productDto) {
        Product entity = new Product();
        copyDtoToEntity(productDto, entity);
        entity = productRepository.save(entity);
        return new ProductDto(entity);
    }

    @Transactional()
    public ProductDto update(Long id, ProductDto productDto) {
        Product entity = productRepository.getReferenceById(id);
        copyDtoToEntity(productDto, entity);
        entity = productRepository.save(entity);
        return new ProductDto(entity);
    }

    @Transactional()
    public void delete(Long id) {
        productRepository.deleteById(id);
    }


    private void copyDtoToEntity(ProductDto dto, Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
    }

}
