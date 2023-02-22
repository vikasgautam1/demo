package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "testimonial"
)
@Data
@NoArgsConstructor
public class Testimonial extends BaseEntity {
    @Column(
            name = "text"
    )
    String text;
    @Column(
            name = "name"
    )
    String name;
    @Column(
            name = "image"
    )
    String image;
    @Column(
            name = "position"
    )
    Integer position = 1;
    @Column(
            name = "module"
    )
    String module;
    @Column(
            name = "status"
    )
    String status;
}
