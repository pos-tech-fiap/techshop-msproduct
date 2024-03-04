package br.com.fiap.techshopmsproduct.repository;

import br.com.fiap.techshopmsproduct.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
