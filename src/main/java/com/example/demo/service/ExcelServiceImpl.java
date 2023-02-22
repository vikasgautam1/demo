package com.example.demo.service;

import com.example.demo.dto.ResourceDto;
import com.example.demo.entities.Testimonial;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExcelServiceImpl implements ExcelService{

    @Override
    public ResourceDto exportToExcel(List<Testimonial> testimonials) {
        Resource resource = prepareExcel(testimonials);
        return ResourceDto.builder().resource(resource).
                mediaType(MediaType.parseMediaType
                        ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")).build();
    }

    private Resource prepareExcel(List<Testimonial> testimonials){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Testimonials");

        prepareHeaders(workbook,sheet,"Id","Name","Text","Status","Module");
        populateData(workbook,sheet,testimonials);

        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){
            workbook.write(byteArrayOutputStream);
            return new ByteArrayResource(byteArrayOutputStream.toByteArray());
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Error while generating excel.");
        }
    }

    private void populateData(Workbook workbook, Sheet sheet,
                              List<Testimonial> testimonials) {

        int rowNo = 1;
        Font font = workbook.createFont();
        font.setFontName("Arial");

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        for(Testimonial testimonial:testimonials){
            int columnNo=0;
            Row row = sheet.createRow(rowNo);
            populateCell(sheet,row,columnNo++,
                    String.valueOf(testimonial.getId()),cellStyle);
            populateCell(sheet,row,columnNo++,
                    testimonial.getName(),cellStyle);
            populateCell(sheet,row,columnNo++,
                    testimonial.getText(),cellStyle);
            populateCell(sheet,row,columnNo++,
                    testimonial.getStatus(),cellStyle);
            populateCell(sheet,row,columnNo++,
                    testimonial.getModule(),cellStyle);
            rowNo++;
        }
    }

    private void populateCell(Sheet sheet,Row row,int columnNo,
                              String value,CellStyle cellStyle){

        Cell cell = row.createCell(columnNo);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
        sheet.autoSizeColumn(columnNo);
    }

    private void prepareHeaders(Workbook workbook,
                                Sheet sheet, String... headers) {

        Row headerRow = sheet.createRow(0);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("Arial");

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        int columnNo = 0;
        for(String header : headers){
            Cell headerCell = headerRow.createCell(columnNo++);
            headerCell.setCellValue(header);
            headerCell.setCellStyle(cellStyle);
        }
    }
}
