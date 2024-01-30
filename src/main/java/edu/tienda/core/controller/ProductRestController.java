package edu.tienda.core.controller;

import edu.tienda.core.services.ProductService;
import edu.tienda.core.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    private ProductService service;
    @GetMapping
    public ResponseEntity<?> getProduct(){
        return ResponseEntity.ok(service.getProducts());
    }
}
