package com.suretrust.search_engine_service.service;


import com.suretrust.search_engine_service.DTO.ProductDTO;
import com.suretrust.search_engine_service.model.Product;

import java.util.List;

public interface ProductService {

    void saveOrUpdateProduct(ProductDTO product);
    public List<Product> searchProducts(String brandName, String categoryName, String productName) ;
}
