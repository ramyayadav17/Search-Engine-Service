package com.suretrust.search_engine_service.service;

import com.suretrust.search_engine_service.DTO.ProductDTO;
import com.suretrust.search_engine_service.model.Product;
import com.suretrust.search_engine_service.repository.ProductRepository;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public ProductServiceImpl(ProductRepository productRepository, ElasticsearchOperations elasticsearchOperations) {
        this.productRepository = productRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public void saveOrUpdateProduct(ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.searchExistingProductByBrandCategoryAndProductName(productDTO.getBrandName(), productDTO.getCategoryName(), productDTO.getName());
        Product product;
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
            product.setName(product.getName());
            product.setBrandName(product.getBrandName());
            product.setCategoryName(product.getCategoryName());
        } else {
            product = new Product();
            product.setName(product.getName());
            product.setBrandName(product.getBrandName());
            product.setCategoryName(product.getCategoryName());
        }
        productRepository.save(product);
    }


    @Override
    public List<Product> searchProducts(String brandName, String categoryName, String productName) {

        brandName = (brandName != null && !brandName.isEmpty()) ? brandName : "*";
        categoryName = (categoryName != null && !categoryName.isEmpty()) ? categoryName : "*";
        productName = (productName != null && !productName.isEmpty()) ? productName : "*";

        return productRepository.searchByBrandCategoryAndProduct(brandName, categoryName, productName);
    }
}