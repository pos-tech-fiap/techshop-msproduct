package br.com.fiap.techshopmsproduct.service;

import br.com.fiap.techshopmsproduct.dto.ProductDTO;
import br.com.fiap.techshopmsproduct.model.Product;
import br.com.fiap.techshopmsproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> pages = productRepository.findAll(pageable);

        return pages.map(ProductDTO::new);
    }
}
