package com.suretrust.search_engine_service.controller;

import com.suretrust.search_engine_service.DTO.ProductDTO;
import com.suretrust.search_engine_service.model.Product;
import com.suretrust.search_engine_service.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public void saveOrUpdateProduct(@RequestBody ProductDTO product) {
        try {
            productService.saveOrUpdateProduct(product);
        }catch (Exception e) {
            LOG.error("Exception while saving product details :{}",product);
        }

    }

    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String productName) {
        return productService.searchProducts(brandName, categoryName, productName);
    }
}
