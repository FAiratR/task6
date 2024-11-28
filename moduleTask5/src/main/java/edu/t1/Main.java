package edu.t1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.*;

@SpringBootApplication(scanBasePackages = "edu.t1")
public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext ctx = SpringApplication.run(Main.class);
        //System.out.println("Hello World module");
    }
}
