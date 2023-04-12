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
@Table(name = "product_list")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "offerid")
    private Integer offerid;

    @Column(name = "hscode")
    private Integer hscode;

    @Column(name = "productname", length = 50)
    private String productname;

    @Column(name = "unit", length = 10)
    private String unit;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "unitprice")
    private Integer unitprice;

}