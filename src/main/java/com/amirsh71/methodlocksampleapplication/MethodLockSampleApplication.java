package com.amirsh71.methodlocksampleapplication;

import com.amirsh71.methodlock.core.LockCacheService;
import com.amirsh71.methodlock.core.inmemory.LockCacheInMemoryServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.amirsh71")
public class MethodLockSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MethodLockSampleApplication.class, args);
    }

    @Bean
    public LockCacheService lockCacheService(){
        return new LockCacheInMemoryServiceImpl();
    }

}
