package com.example.demo.service;

import com.example.demo.dto.ResourceDto;
import com.example.demo.entities.Testimonial;

import java.util.List;

public interface ExcelService {
    ResourceDto exportToExcel(List<Testimonial> testimonials);
}
