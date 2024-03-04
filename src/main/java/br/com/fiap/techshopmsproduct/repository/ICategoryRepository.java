package br.com.fiap.techshopmsproduct.repository;

import br.com.fiap.techshopmsproduct.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
