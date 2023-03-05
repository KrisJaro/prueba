package com.example.backendproducts.service;

import com.example.backendproducts.dto.ProductDTO;

import reactor.core.publisher.Flux;

public interface ProductService {

	Flux<ProductDTO> getProduct(Long productId);

}
