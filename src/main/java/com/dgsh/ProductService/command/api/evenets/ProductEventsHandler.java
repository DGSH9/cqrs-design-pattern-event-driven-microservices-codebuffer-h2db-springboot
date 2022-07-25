package com.dgsh.ProductService.command.api.evenets;

import com.dgsh.ProductService.command.api.data.Product;
import com.dgsh.ProductService.command.api.data.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {


    private ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvents event){
        Product product = new Product();
        BeanUtils.copyProperties(event,product);
        productRepository.save(product);
    }


}
