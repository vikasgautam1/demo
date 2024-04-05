package com.example.demo.designPatterns.singletonDesignPattern;

import com.example.demo.DemoApplication;
import com.example.demo.service.Samosa;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class SingletonDesignPattern {

    public static void main(String[] args) throws Exception {

        System.out.println("Everything is working fine...!");

		Samosa samosa1 = Samosa.getSamosa();
		System.out.println(samosa1.hashCode());

        // Break singleton design pattern
//        1. Reflection api
//        --> Solution 1: throw exception in private constructor if object already created.
//         private Samosa(){
//              if(samosa != null){
//                  throw new RuntimeException("Trying to break singleton design pattern");
//              }
//          }
//        --> Solution 2: Use Enum instead of Class
//        2. Deserialization of object
//        --> Solution: Use implement readResolve() method in Samosa class
//              public Object readResolve(){
//                  return samosa;
//               }
//        3. Cloning
//        --> Solution: return existing same samosa in overrided method clone()


        // 1. Reflection api to break singleton design pattern
//		Constructor<Samosa> constructor = Samosa.class.getDeclaredConstructor();
//		constructor.setAccessible(true);
//		Samosa samosa2 = constructor.newInstance();
//		System.out.println(samosa2.hashCode());

        // 2. Deserialization
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("samosa.ob"));
//        oos.writeObject(samosa1);
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("samosa.ob"));
//        Samosa samosa3 = (Samosa) ois.readObject();
//        System.out.println(samosa3.hashCode());

        // 3. Cloning
//        Samosa samosa4 = (Samosa) samosa1.clone();
//        System.out.println(samosa4.hashCode());

    }
}
