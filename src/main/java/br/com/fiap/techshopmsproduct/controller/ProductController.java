package br.com.fiap.techshopmsproduct.controller;

import br.com.fiap.techshopmsproduct.dto.ProductDTO;
import br.com.fiap.techshopmsproduct.dto.SubtractProductDTO;
import br.com.fiap.techshopmsproduct.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> pages = productService.findAll(pageable);

        return ResponseEntity.ok(pages);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        var product = productService.findById(id);

        return ResponseEntity.ok(product);
    }

    @PutMapping(value = "/subtractProduct")
    public ResponseEntity subtractProduct(@RequestBody @Valid SubtractProductDTO subtractProductDTO) {
        productService.subtractProduct(subtractProductDTO);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody @Valid ProductDTO productDTO) {
        var product = productService.save(productDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        var product = productService.update(id, productDTO);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Produto removido com sucesso!");
    }
}
