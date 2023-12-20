package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
@Slf4j
public class PartitionService {

    public static void generatePartitions() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd_MM_yy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, 3, 29);
        for (int i = 0; i <= 36; i++) {
            calendar.add(Calendar.DATE, -7);
            Date date1 = calendar.getTime();
            calendar.add(Calendar.DATE, 7);
            Date date2 = calendar.getTime();
            String date2String = sdf.format(date2);
            Date date2StartDay = sdf.parse(date2String);
            System.out.println("PARTITION p_" + sdf1.format(date1) + " VALUES LESS THAN (" + date2StartDay.getTime()/1000 + ") ENGINE = InnoDB,");
            calendar.add(Calendar.DATE, 7);
        }
    }
}
