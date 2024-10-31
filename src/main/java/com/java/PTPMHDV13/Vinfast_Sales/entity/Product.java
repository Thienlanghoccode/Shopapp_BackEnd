package com.java.PTPMHDV13.Vinfast_Sales.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 250)
    private String productName;

    @Column(name = "price")
    private Double productPrice;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "image")
    private String productImage;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    @JsonBackReference
    private Category category;
}
