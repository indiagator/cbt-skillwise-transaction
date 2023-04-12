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
@Table(name = "sellers")
public class Seller {
    @Id
    @Column(name = "username", nullable = false, length = 50)
    private String id;

    @Column(name = "wallet_id", length = 50)
    private String walletId;

    @Column(name = "organization", length = 50)
    private String organization;

}