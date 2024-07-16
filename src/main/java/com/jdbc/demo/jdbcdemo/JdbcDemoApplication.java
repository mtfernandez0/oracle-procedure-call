package com.jdbc.demo.jdbcdemo;

import com.jdbc.demo.jdbcdemo.service.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JdbcDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JdbcDemoApplication.class, args);

        DemoService service = context.getBean(DemoService.class);

        service.callDemoProcedure();
    }
}
