package com.store.api.persistence.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude = {"productList"})
@Builder
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id", nullable = false, unique = true, updatable = false)
    private Long saleId;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private LocalDate createAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private LocalDate updateAt;

    @Column(name ="total_amount", nullable = false)
    private double totalAmount;

    @ManyToMany(mappedBy = "salesList", targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> productsList;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @PrePersist
    public void prePersist(){
        this.createAt = LocalDate.now();
    }

    @PostUpdate
    public void postUpdate(){
        this.updateAt = LocalDate.now();
    }
}
