package com.example.scoresservice.proxies;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="data-provider")
public interface DataProviderProxy {
    @GetMapping("/data-provider/v1/user/name/{id}")
    public String retrieveUserName(@PathVariable String id);

    @GetMapping("/data-provider/v1/test/name/{id}")
    public String retrieveTestName(@PathVariable long id);
}
