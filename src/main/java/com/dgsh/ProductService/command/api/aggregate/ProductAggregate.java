package com.dgsh.ProductService.command.api.aggregate;

import com.dgsh.ProductService.command.api.commands.CreateProductCommand;
import com.dgsh.ProductService.command.api.evenets.ProductCreatedEvents;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;


@CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand){
        //You can perform all the validations
        ProductCreatedEvents productCreatedEvents = new ProductCreatedEvents();

        //it will copy all property from createProductCommand to productCreatedEvents
        BeanUtils.copyProperties(createProductCommand,productCreatedEvents);

        AggregateLifecycle.apply(productCreatedEvents);
    }
    public ProductAggregate(){
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvents productCreatedEvents) {
        this.quantity = productCreatedEvents.getQuantity();
        this.productId = productCreatedEvents.getProductId();
        this.name = productCreatedEvents.getName();
        this.price = productCreatedEvents.getPrice();
    }


}
