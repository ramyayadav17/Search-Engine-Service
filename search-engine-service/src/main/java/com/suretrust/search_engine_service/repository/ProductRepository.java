package com.suretrust.search_engine_service.repository;


import com.suretrust.search_engine_service.model.Product;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
    @Query("{\"bool\": {\"should\": [" +
            "{\"match\": {\"brandName\": \"?0\"}}," +
            "{\"match\": {\"categoryName\": \"?1\"}}," +
            "{\"match\": {\"name\": \"?2\"}}" +
            "]}}")
    List<Product> searchByBrandCategoryAndProduct(String brandName, String categoryName, String productName);

    @Query("{\"bool\": {\"should\": [" +
            "{\"match\": {\"brandName\": \"?0\"}}," +
            "{\"match\": {\"categoryName\": \"?1\"}}," +
            "{\"match\": {\"name\": \"?2\"}}" +
            "]}}")
    Optional<Product> searchExistingProductByBrandCategoryAndProductName(String brandName, String categoryName, String productName);

}