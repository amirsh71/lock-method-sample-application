package com.amirsh71.methodlocksampleapplication.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderFoodRequest {
    private Long customerId;
    private FoodDto food;
    private RestaurantDto restaurant;

}
