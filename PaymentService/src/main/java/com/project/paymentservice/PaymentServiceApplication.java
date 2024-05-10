package com.project.paymentservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class PaymentServiceApplication {
	private static final Logger LOG = LoggerFactory.getLogger(PaymentServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}
	@Autowired
	PaymentService paymentManageService;

	@Bean
	public NewTopic orders() {
		return TopicBuilder.name("orders")
				.partitions(3)
				.compact()
				.build();
	}
	@KafkaListener(id = "orders", topics = "orders", groupId = "payment")
	public void onEvent(Order o) {
		LOG.info("Received: {}" , o);
		if (o.getStatus().equals("NEW"))
			paymentManageService.reserve(o);
		else
			paymentManageService.confirm(o);
	}


}
