package com.kunal.module1Introduction;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Component
//@Service
//@Repository
//@Controller
//@RestController
public class PaymentService {
    public void pay()
    {
        System.out.println("Paying..");
    }
    @PostConstruct
    public void afterInit(){
        System.out.println("Before paying...");
    }

    @PreDestroy
    public void beforeDestroyy()
    {
        System.out.println("After Paying...");
    }

}


