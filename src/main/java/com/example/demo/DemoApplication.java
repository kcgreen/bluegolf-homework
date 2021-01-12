package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(DemoApplication.class);

        ApplicationContext c = sa.run(args);
        MyObject bean = c.getBean(MyObject.class);
        bean.doSomething();
    }
	
    @Component
    private static class MyObject {

        public void doSomething () {
            System.out.println("-------------");
            System.out.println("working ...");
            System.out.println("-------------");
        }
    }
	
}
