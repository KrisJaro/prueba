package com.example.backendproducts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backendproducts.dto.ProductDTO;
import com.example.backendproducts.service.ProductService;

import reactor.core.publisher.Flux;

/**
 * Clase para consultar el prodcutorepresenta un objeto Producto.
 * 
 * @author Javier Rodriguez Herrera
 * 
 * @param productId
 * @return Product
 */
@RestController
@RequestMapping("/product")
@Validated
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/{productId}/similar")
	public Flux<ProductDTO> name(@PathVariable Long productId) {
		return productService.getProduct(productId);
	}

}
