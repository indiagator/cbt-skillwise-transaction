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
@Table(name = "product_offers")
public class ProductOffer {
    @Id
    @Column(name = "offerid", nullable = false)
    private Integer id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "offername", length = 50)
    private String offername;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "status", length = 20)
    private String status;

}