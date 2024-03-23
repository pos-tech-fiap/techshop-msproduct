package br.com.fiap.techshopmsproduct.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigInteger;

public record SubtractProductDTO(@NotNull Long id, @PositiveOrZero BigInteger quantity) {
}
