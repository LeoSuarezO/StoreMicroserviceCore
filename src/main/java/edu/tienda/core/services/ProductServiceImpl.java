package edu.tienda.core.services;

import edu.tienda.core.domain.Client;
import edu.tienda.core.domain.Product;
import edu.tienda.core.excceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(001, "Smart TV", 1500.0, 15),
            new Product(002, "NoteBook", 2100.0, 22),
            new Product(003, "Phone", 800.0, 18))
    );

    @Override
    public List<Product> getProducts(){
        return products;
    }

    public Product getProductById(Integer id){
        return products.stream()
                .filter(productTemp -> productTemp.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Producto con id "+id+" no encontrado"));
    }
}
