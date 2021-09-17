package com.example.demo.controller;

import com.example.demo.api.GreetingService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @DubboReference
    private GreetingService greetingService;

    @GetMapping("/test")
    public String sayHello(String name, String sid) {
        RpcContext.getClientAttachment().setAttachment("sid", sid);
        return greetingService.sayHello(name);
    }
}
