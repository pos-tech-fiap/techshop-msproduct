package br.com.fiap.techshopmsproduct.service;

import br.com.fiap.techshopmsproduct.dto.ProductDTO;
import br.com.fiap.techshopmsproduct.dto.SubtractProductDTO;
import br.com.fiap.techshopmsproduct.model.Product;
import br.com.fiap.techshopmsproduct.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> pages = productRepository.findAll(pageable);

        return pages.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        return new ProductDTO(product);
    }

    @Transactional
    public void subtractProduct(SubtractProductDTO subtractProductDTO) {
        var product = productRepository.findById(subtractProductDTO.id())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado, id:" + subtractProductDTO.id()));
        var newQuantity = updateQuantity(product.getQuantity(), subtractProductDTO.quantity());
        product.setQuantity(newQuantity);
        productRepository.save(product);
    }

    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        try {
            Product product = new Product();
            mapperDtoToEntity(productDTO, product);
            var productSaved = productRepository.save(product);

            return new ProductDTO(productSaved);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao adicionar o produto: " + e);
        }
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        try {
            Product product = productRepository.getOne(id);
            mapperDtoToEntity(productDTO, product);
            var productSaved = productRepository.save(product);

            return new ProductDTO(productSaved);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Produto não encontrado, id: "+ id);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar o produto: " + e);
        }
    }

    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw  new RuntimeException("Produto não encontrado, id: " + id);
        }
    }

    private void mapperDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setQuantity(dto.quantity());
    }

    private BigInteger updateQuantity(BigInteger currentQuantity, BigInteger requestedQuantity) {
        final BigInteger newQuantity = currentQuantity.subtract(requestedQuantity);
        if (newQuantity.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalStateException("Sem estoque suficiente!");
        } else {
            return newQuantity;
        }
    }
}
