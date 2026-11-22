package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDto;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.excepitons.DatabaseException;
import com.devsuperior.dscommerce.services.excepitons.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto getProductById(Long id){
          Optional<Product> result =  productRepository.findById(id);
          Product product2 = result.
                  orElseThrow(()-> new ResourceNotFoundException("Produto não encotrado"));;
          return new ProductDto(product2);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> listProduct(Pageable pageable) {
        Page<Product> product = productRepository.findAll(pageable);
        return product.map(x-> new ProductDto(x));
    }

    private void tranferirDados(ProductDto produtcDto, Product product){
        product.setId(produtcDto.getId());
        product.setName(produtcDto.getName());
        product.setDescription(produtcDto.getDescription());
        product.setPrice(produtcDto.getPrice());
        product.setImgUrl(produtcDto.getImgUrl());
    }

    @Transactional
    public ProductDto inserirDados(ProductDto produtcDto){
        Product product = new Product();
        tranferirDados(produtcDto,product);
        product = productRepository.save(product);
        return new ProductDto(product);
    }

    @Transactional
    public ProductDto atualizarDados(Long id,ProductDto produtcDto){
        try{
            Product product = productRepository.getReferenceById(id);
            tranferirDados(produtcDto,product);
            product =  productRepository.save(product);
            return new ProductDto(product);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Produto não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletarDados(Long id){
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        try{
            productRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Violação do Banco de Dados");
        }
    }
}
