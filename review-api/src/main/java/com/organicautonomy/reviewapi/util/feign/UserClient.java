package com.organicautonomy.reviewapi.util.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user-service")
@RequestMapping(name = "/users")
public interface UserClient {

}
