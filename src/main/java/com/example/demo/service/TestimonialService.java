package com.example.demo.service;

import com.example.demo.dao.TestimonialDao;
import com.example.demo.entities.Testimonial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TestimonialService {

    @Autowired
    TestimonialDao testimonialDao;

    public List<Testimonial> getTestimonials(){
        log.info("Getting testimonials...");
        List<Testimonial> testimonials = testimonialDao.findTop20ByOrderByCreatedAtDesc();
        return testimonials;
    }
}
