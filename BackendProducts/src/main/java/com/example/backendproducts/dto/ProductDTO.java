package com.example.backendproducts.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

	private Long id;
	private String name;
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private BigDecimal price;
	private boolean availability;
	private String message;

	public ProductDTO(String message) {
		this.message = message;
	}

}
