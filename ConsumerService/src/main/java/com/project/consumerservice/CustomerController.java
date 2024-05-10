package com.project.consumerservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerRepository customerRepository;


    @PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody Customer cust) {
        // order.setId((int) (Math.random() * 10000));
        customerRepository.save(cust);

        LOG.info("Sent: {}", cust);
        return  ResponseEntity.ok(cust);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> all() {
        List<Customer> lstCustomers = customerRepository.findAll();
        return ResponseEntity.ok(lstCustomers);
    }

   @GetMapping("/getCustomerByEmail/{email}")
    public ResponseEntity<List<Customer>> all(@PathVariable String email) {
        List<Customer> lstCustomers = customerRepository.getCustomerByEmail(email);
        return ResponseEntity.ok(lstCustomers);
    }
}