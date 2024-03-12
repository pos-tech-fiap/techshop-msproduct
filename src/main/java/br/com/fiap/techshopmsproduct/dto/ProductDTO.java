package br.com.fiap.techshopmsproduct.dto;

import br.com.fiap.techshopmsproduct.model.Product;

import java.math.BigInteger;

public record ProductDTO(String name, String description, BigInteger quantity, Double price) {


    public ProductDTO(Product product) {
        this(product.getName(), product.getDescription(), product.getQuantity(), product.getPrice());
    }
}
