package com.project.orderprocessservice;

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
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);
    private AtomicLong id = new AtomicLong();
    private KafkaTemplate<Integer, Order> template;
    private StreamsBuilderFactoryBean kafkaStreamsFactory;
    //private OrderGeneratorService orderGeneratorService;

    @Autowired
    OrderRepository orderRepository;

    public OrderController(KafkaTemplate<Integer, Order> template,
                           StreamsBuilderFactoryBean kafkaStreamsFactory){

        this.template = template;
        this.kafkaStreamsFactory = kafkaStreamsFactory;

    }

    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody Order order) {
       // order.setId((int) (Math.random() * 10000));
        orderRepository.save(order);
        template.send("orders", order.getId(), order);
        LOG.info("Sent: {}", order);
        return  ResponseEntity.ok(order);
    }



    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> all() {
        List<Order> lstOrders =  orderRepository.findAll();
        return ResponseEntity.ok(lstOrders);
    }
}