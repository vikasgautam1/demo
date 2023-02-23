package com.example.demo.service;

import com.example.demo.entities.Testimonial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CsvExportService {

    private static String filePath = "/Users/vikasgautam/Documents/Docs/";

    @Autowired
    TestimonialService testimonialService;

    public void exportTestimonialsToCsv(){

        try {
            String fileName = "testimonials"+getCurrDateTime()+".csv";

            Writer writer = new FileWriter(filePath+fileName);
            ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"ID", "Text", "Name", "Module", "Status"};
            String[] nameMapping = {"id", "text", "name", "module", "status"};

            csvWriter.writeHeader(csvHeader);

            List<Testimonial> testimonials = testimonialService.getTestimonials();

            for (Testimonial testimonial : testimonials) {
                csvWriter.write(testimonial, nameMapping);
            }

            csvWriter.close();
        }
        catch (Exception ex){
            log.error("Exception in exporting data to CSV",ex);
        }
    }

    private String getCurrDateTime(){
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        return dateFormatter.format(new Date());
    }

}
