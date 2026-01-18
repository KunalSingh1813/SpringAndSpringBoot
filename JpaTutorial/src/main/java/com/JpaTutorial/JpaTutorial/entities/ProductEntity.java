package com.JpaTutorial.JpaTutorial.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(
        name = "product_table",
        uniqueConstraints = {
                @UniqueConstraint(name="sku_unique", columnNames = {"sku"}),
                @UniqueConstraint(name="title_price_unique", columnNames = {"Product_Title","price"})
        },
        indexes = {
                @Index(name = "idx_sku", columnList = "sku")
        }


)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

    @Column(nullable = false, length = 30)
     String sku;

     @Column(name="Product_Title")
     String title;
     BigDecimal price;
     Integer quantity;

     @CreationTimestamp
     LocalDateTime createdAt;

     @UpdateTimestamp
     LocalDateTime updatedAt;


}
