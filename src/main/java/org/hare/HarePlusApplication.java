package org.hare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hare
 */
@RestController
@SpringBootApplication
public class HarePlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarePlusApplication.class, args);
    }

}