package com.gmail.erofeev.st.alexei.thirdweek.controller.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "com.gmail.erofeev.st.alexei.thirdweek.repository",
        "com.gmail.erofeev.st.alexei.thirdweek.service",
        "com.gmail.erofeev.st.alexei.thirdweek.controller"})
public class SpringBootModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootModuleApplication.class, args);
    }

}
