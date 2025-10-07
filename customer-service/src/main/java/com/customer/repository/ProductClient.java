package com.customer.repository;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "product-service")
public interface ProductClient {



}
