package com.example.backendproducts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.example.backendproducts.dto.ProductDTO;
import com.example.backendproducts.service.ProductServiceSimilarIds;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceSimilarIdsImpl implements ProductServiceSimilarIds {

	@Value("${urlServiceProductId}")
	private String urlServiceProductId;

	@Value("${urlServiceSimilarIds}")
	private String urlServiceSimilarIds;

	private final WebClient webClient;

	@Autowired
	public ProductServiceSimilarIdsImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
	}

	@Override
	public Mono<ProductDTO> getProduct(Long productId) {
		Mono<ProductDTO> response = webClient.get().uri(urlServiceProductId + productId).retrieve()
				.bodyToMono(ProductDTO.class).onErrorResume(WebClientResponseException.class, ex -> {
					if (ex.getStatusCode().is4xxClientError() && ex.getStatusCode() == HttpStatus.NOT_FOUND) {
						return Mono.just(new ProductDTO(null, null, null, false, "Product not found"));
					} else if (ex.getStatusCode().is5xxServerError()
							&& ex.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
						return Mono.just(new ProductDTO(null, null, null, false, null));
					} else {
						return Mono.error(ex);
					}
				});

		return response;
	}

	@Override
	public Flux<String> getSimilarIds(Long productId) {
		Flux<String> response = webClient.get().uri(urlServiceSimilarIds + productId + "/similarids").retrieve()
				.bodyToFlux(String.class).onErrorResume(WebClientResponseException.class, ex -> {
					if (ex.getStatusCode().is4xxClientError() && ex.getStatusCode() == HttpStatus.NOT_FOUND) {
						return Flux.just("");
					} else if (ex.getStatusCode().is5xxServerError()
							&& ex.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
						return Flux.just(new String());
					} else {
						return Flux.error(ex);
					}
				});
		return response;
	}

}
