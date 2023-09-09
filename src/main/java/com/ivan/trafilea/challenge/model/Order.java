package com.ivan.trafilea.challenge.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Order {

    @Id @GeneratedValue private Long orderId;

    @OneToOne
    private Cart cart;


}
