package br.com.fiap.techshopmsproduct.dto;

import br.com.fiap.techshopmsproduct.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigInteger;

public record ProductDTO(Long id, @NotBlank String name, String description, @PositiveOrZero BigInteger quantity, @NotNull Double price) {

    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getQuantity(), product.getPrice());
    }
}
