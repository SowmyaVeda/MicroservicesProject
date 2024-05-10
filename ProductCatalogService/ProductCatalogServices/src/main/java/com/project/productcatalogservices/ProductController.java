package com.project.productcatalogservices;

import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductRepository productRepository;



    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product prod) {
        // order.setId((int) (Math.random() * 10000));
        productRepository.save(prod);
        //template.send("orders", order.getId(), order);
        LOG.info("Sent: {}", prod);
        return  ResponseEntity.ok(prod);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> all() {
        List<Product> lstProds =  productRepository.findAll();
        return ResponseEntity.ok(lstProds);
    }
}