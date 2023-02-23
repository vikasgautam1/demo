package com.example.demo.service;

import com.example.demo.entities.Testimonial;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
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

    public boolean createCsvFile(String filePath, String fileName){
        try {
            FileWriter out = new FileWriter(filePath + fileName);
            CSVPrinter printer = CSVFormat.DEFAULT
                    .withHeader("ID", "Text", "Name", "Module", "Status").print(out);
            printer.flush();
            printer.close();
            log.info("File with name: {} created successfully",fileName);
            return true;
        }
        catch (Exception ex){
            log.error("Error in creating file with fileName: {}",fileName,ex);
            return false;
        }
    }

    public void writeIntoFile(String filePath, String fileName){
        try{
            fileName = getCurrDateTime()+".csv";
            CSVPrinter printer = new CSVPrinter(new FileWriter(filePath + fileName), CSVFormat.DEFAULT);
            printer.printRecord("ID", "Text", "Name", "Module", "Status");

            List<Testimonial> testimonials = testimonialService.getTestimonials();
            for(Testimonial testimonial: testimonials){
                String[] record = {testimonial.getId().toString(), testimonial.getText(), testimonial.getName(), testimonial.getModule(), testimonial.getStatus()};
                printer.printRecord(record);
            }

            printer.flush();
            printer.close();

        }
        catch (Exception ex){
            log.error("Error in writing into file",ex);
        }

    }

    private String getCurrDateTime(){
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyHHmmss");
        return dateFormatter.format(new Date());
    }

}
