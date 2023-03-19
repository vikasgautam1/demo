package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class TestService {

    public static void test(){
        try{
            LocalDate date = LocalDate.now();
            DayOfWeek dow = date.getDayOfWeek();
            String day = dow.name();
            System.out.println(day);

            String payeeVpa = "ginbilog@fbl";
            String beneAccNo = payeeVpa.split("@")[0];
            String subStr = beneAccNo.substring(9);
            System.out.println(beneAccNo + "_" + subStr+"1");
        }
        catch (Exception ex){
            System.out.println("Error in conversion " + ex);
        }
    }
}
