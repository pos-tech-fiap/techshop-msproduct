package br.com.fiap.techshopmsproduct.dto;

import br.com.fiap.techshopmsproduct.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

public record ProductDTO(@NotBlank String name, String description, @NotNull BigInteger quantity, @NotNull Double price) {


    public ProductDTO(Product product) {
        this(product.getName(), product.getDescription(), product.getQuantity(), product.getPrice());
    }
}
