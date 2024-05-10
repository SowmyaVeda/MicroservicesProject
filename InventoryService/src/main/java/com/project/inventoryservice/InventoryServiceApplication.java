package com.project.inventoryservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
@EnableKafka
public class InventoryServiceApplication {
	private static final Logger LOG = LoggerFactory.getLogger(InventoryServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	public NewTopic orders() {
		return TopicBuilder.name("orders")
				.partitions(3)
				.compact()
				.build();
	}

	@Bean
	public NewTopic paymentTopic() {
		return TopicBuilder.name("payment-orders")
				.partitions(3)
				.compact()
				.build();
	}

	@Bean
	public NewTopic stockTopic() {
		return TopicBuilder.name("stock-orders")
				.partitions(3)
				.compact()
				.build();
	}


	@Autowired
	InventoryService inventoryService;

	@KafkaListener(id = "orders", topics = "orders", groupId = "payment")
	public void onEvent(Order o) {
		LOG.info("Received: {}" , o);
		if (o.getStatus().equals("NEW"))
			inventoryService.reserve(o);
		else
			inventoryService.confirm(o);
	}


}
