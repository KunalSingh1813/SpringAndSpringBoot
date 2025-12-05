package com.kunal.module1Introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Module1IntroductionApplication implements CommandLineRunner {


	//If it is singleton scope then hashCode will be same for both objects
	//If scope is prototype then hashCode will be different


	//@Autowired
//	final NotificationService notificationServiceObj; //Dependency Injection

//	public Module1IntroductionApplication(
//			 NotificationService notificationServiceObj){
//		     this.notificationServiceObj = notificationServiceObj;    //Constructor dependency injection //Preferred
//	}
	@Autowired
	Map<String, NotificationService> notificationServiceMap = new HashMap<>();

	public static void main(String[] args) {
	 SpringApplication.run(Module1IntroductionApplication.class,args);

	}


	@Override
	public void run(String... args) throws Exception {
		//notificationServiceObj.send("Hello");
		for(var notificationService: notificationServiceMap.entrySet())
		{
			System.out.println(notificationService.getKey());
			notificationService.getValue().send("Hello");
		}
	}
}
