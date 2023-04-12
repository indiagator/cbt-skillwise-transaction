package com.skillwise.cbtskillwisetransaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "orderid", nullable = false)
    private Integer id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "offerid")
    private Integer offerid;

    @Column(name = "bid")
    private Integer bid;

    @Column(name = "comments", length = Integer.MAX_VALUE)
    private String comments;

}