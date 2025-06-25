package com.giftydelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GiftyDeliverySystemFullApplication {
    public static void main(String[] args) {
        SpringApplication.run(GiftyDeliverySystemFullApplication.class, args);
    }
}
