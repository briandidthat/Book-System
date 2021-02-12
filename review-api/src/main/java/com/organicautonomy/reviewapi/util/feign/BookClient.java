package com.organicautonomy.reviewapi.util.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "book-service")
public interface BookClient {
}
