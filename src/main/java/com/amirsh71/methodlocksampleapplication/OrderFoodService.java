package com.amirsh71.methodlocksampleapplication;

import com.amirsh71.methodlock.core.Lock;
import com.amirsh71.methodlocksampleapplication.model.OrderFoodRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Log4j2
public class OrderFoodService {

    @Lock(type = "ORDER_FOOD", params = {"#orderFoodRequest.food.foodId"})
    public void orderFood(@RequestBody OrderFoodRequest orderFoodRequest) {
        log.info("calling orderFood with request={}", orderFoodRequest.toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
