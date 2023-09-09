package com.ivan.trafilea.challenge.model;

import com.ivan.trafilea.challenge.model.enums.ECategory;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
@EqualsAndHashCode
@Entity
@Table(name = "product")
public class Product {
    private  @Id @GeneratedValue Long productId;
    private String name;
    private ECategory category;
    private Integer price;

    @OneToMany(mappedBy = "product")
    private List<ProductCart> productCarts;

    public Product (String name, ECategory category, Integer price){
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ECategory getCategory() {
        return category;
    }

    public void setCategory(ECategory category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<ProductCart> getProductCarts() {
        return productCarts;
    }

    public void setProductCarts(List<ProductCart> productCarts) {
        this.productCarts = productCarts;
    }
}
