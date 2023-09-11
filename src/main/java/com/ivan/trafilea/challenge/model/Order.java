package com.ivan.trafilea.challenge.model;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "orders")
@ApiModel
public class Order {

    @Id @GeneratedValue private Long orderId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId", referencedColumnName = "cartId")
    private Cart cart;

    private double totalProducts;
    private double totalDiscounts;
    private double totalShipping;
    private double totalOrder;
}
