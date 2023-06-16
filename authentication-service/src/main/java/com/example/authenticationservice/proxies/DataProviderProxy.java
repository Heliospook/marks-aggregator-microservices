package com.example.authenticationservice.proxies;

import com.example.authenticationservice.models.RegisterRequest;
import com.example.authenticationservice.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="data-provider")
public interface DataProviderProxy {
    @PostMapping("/data-provider/v1/user")
    public User createUser(@RequestBody RegisterRequest req);
}

