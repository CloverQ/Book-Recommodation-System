package com.sakura.book_recommodation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sakura.book_recommodation")
@MapperScan("com.sakura.book_recommodation.mapper")
public class BookRecommodationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookRecommodationApplication.class, args);
    }

}
