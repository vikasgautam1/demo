package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id"
    )
    @Id
    private Long id;
    @Column(
            name = "created_at"
    )
    private Date createdAt;
    @Column(
            name = "updated_at"
    )
    private Date updatedAt;

    @PrePersist
    void createDate() {
        if (this.createdAt == null) {
            this.setCreatedAt(new Date());
        }

        this.setUpdatedAt(new Date());
    }

    @PreUpdate
    void updatedAt() {
        this.setUpdatedAt(new Date());
    }
}