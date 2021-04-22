package com.amirsh71.methodlocksampleapplication;

import com.amirsh71.methodlock.core.OperationLockedException;
import com.amirsh71.methodlocksampleapplication.model.FoodDto;
import com.amirsh71.methodlocksampleapplication.model.OrderFoodRequest;
import com.amirsh71.methodlocksampleapplication.model.RestaurantDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class MethodLockSampleApplicationTests {
    @Autowired
    private OrderFoodService orderFoodService;

    @Test
    public void test() throws Exception {
        OrderFoodRequest foodRequest1 = OrderFoodRequest.builder()
                .customerId(200L)
                .food(FoodDto.builder().foodId(100L).build())
                .restaurant(RestaurantDto.builder().restaurantId(100L).build())
                .build();
        OrderFoodRequest foodRequest2 = OrderFoodRequest.builder()
                .customerId(200L)
                .food(FoodDto.builder().foodId(200L).build())
                .restaurant(RestaurantDto.builder().restaurantId(100L).build())
                .build();

        AtomicInteger numberOfSuccess = new AtomicInteger();
        AtomicInteger numberOfFail = new AtomicInteger();
        Runnable runnable1 = () -> {
            try {
                orderFoodService.orderFood(foodRequest1);
                numberOfSuccess.getAndIncrement();
            } catch (OperationLockedException e) {
                numberOfFail.getAndIncrement();
            }
        };
        Runnable runnable2 = () -> {
            try {
                orderFoodService.orderFood(foodRequest2);
                numberOfSuccess.getAndIncrement();
            } catch (OperationLockedException e) {
                numberOfFail.getAndIncrement();
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(runnable1).start();
            new Thread(runnable2).start();
        }

        Thread.sleep(10000);

        Assertions.assertEquals(2, numberOfSuccess.get());
        Assertions.assertEquals(4, numberOfFail.get());
    }
}