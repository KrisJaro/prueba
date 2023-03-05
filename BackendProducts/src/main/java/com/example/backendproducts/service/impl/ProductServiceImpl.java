package com.example.backendproducts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backendproducts.dto.ProductDTO;
import com.example.backendproducts.service.ProductService;
import com.example.backendproducts.service.ProductServiceSimilarIds;

import reactor.core.publisher.Flux;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductServiceSimilarIds productServiceSimilarIds;

	@Override
	public Flux<ProductDTO> getProduct(Long productId) {
		Flux<ProductDTO> products = productServiceSimilarIds.getSimilarIds(productId).flatMap(idList -> {
			String[] ids = idList.replaceAll("\\[|\\]|\\s", "").split(",");
			return Flux.fromArray(ids);
		}).concatMap(id -> {
			return productServiceSimilarIds.getProduct(Long.valueOf(id));
		}).map(product -> {
			return product;
		});

		products.subscribe();

		return products;

	}

}
