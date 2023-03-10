package com.example.demo.controller;

import com.example.demo.dto.ResourceDto;
import com.example.demo.entities.Testimonial;
import com.example.demo.service.CsvExportService;
import com.example.demo.service.ExcelGeneratorService;
import com.example.demo.service.ExcelService;
import com.example.demo.service.TestimonialService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class TestimonialController {

    @Autowired
    TestimonialService testimonialService;

    @Autowired
    ExcelService excelService;

    @Autowired
    CsvExportService csvExportService;

    @GetMapping("/testimonials")
    public ResponseEntity<Object> getTestimonials(HttpServletResponse httpServletResponse) {
        try{
            log.info("Request received to get latest 20 testimonials");
            List<Testimonial> testimonials = testimonialService.getTestimonials();

            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=records_" + currentDateTime + ".xlsx";
            httpServletResponse.setHeader(headerKey, headerValue);

            ExcelGeneratorService excelGeneratorService = new ExcelGeneratorService(testimonials);
            excelGeneratorService.generate(httpServletResponse);
            return new ResponseEntity<>(testimonials, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error("Exception in getting testimonials into excel",ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/testimonials/export-excel-to-specified-location")
    public ResponseEntity<Object> getTestimonials() {
        try{
            log.info("Request received to get latest 20 testimonials");
            List<Testimonial> testimonials = testimonialService.getTestimonials();

            ResourceDto resourceDto = excelService.exportToExcel(testimonials);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition",
                    "attachment; filename="+"Testimonials.xlsx");

            ResponseEntity.ok()
                    .contentType(resourceDto.getMediaType())
                    .headers(httpHeaders)
                    .body(resourceDto.getResource());

            return new ResponseEntity<>(testimonials, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error("Exception in getting testimonials into excel",ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/testimonials/export-to-csv")
    public ResponseEntity<Object> getTestimonialsIntoCsv() {
        try{
            csvExportService.exportTestimonialsToCsv();
            log.info("Data successfully exported to csv file");
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error("Exception in getting testimonials into csv",ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static String filePath = "/Users/vikasgautam/Documents/Docs/";

    @GetMapping("/testimonials/export-to-csv-new-library")
    public ResponseEntity<Object> getTestimonialsIntoCsvApache() {
        try{
            String fileName = "testimonials_test_1.csv";
//            boolean isFileCreated = csvExportService.createCsvFile(filePath, fileName);
//            if(isFileCreated){
//
//            }
            csvExportService.writeIntoFile(filePath, fileName);
            log.info("Data successfully exported to csv file");
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error("Exception in getting testimonials into csv",ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
