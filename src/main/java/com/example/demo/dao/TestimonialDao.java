package com.example.demo.dao;

import com.example.demo.entities.Testimonial;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestimonialDao extends CrudRepository<Testimonial, Long> {
    List<Testimonial> findTop20ByOrderByCreatedAtDesc();
}
