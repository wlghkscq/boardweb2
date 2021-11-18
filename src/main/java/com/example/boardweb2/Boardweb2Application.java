package com.example.boardweb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@EnableJpaAuditing // JPA Auditing 기능
@SpringBootApplication
public class Boardweb2Application {

    public static void main(String[] args) {
        SpringApplication.run(Boardweb2Application.class, args);
    }

    //HiddenHttpMethodFilter를 Bean으로 등록하여, @PutMapping과 @DeleteMapping이 작동할 수 있도록 한다.
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        return new HiddenHttpMethodFilter();
    }

}
