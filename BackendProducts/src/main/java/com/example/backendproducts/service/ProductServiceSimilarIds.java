package com.example.backendproducts.service;

import com.example.backendproducts.dto.ProductDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductServiceSimilarIds {

	Flux<String> getSimilarIds(Long productId);

	Mono<ProductDTO> getProduct(Long productId);

}
