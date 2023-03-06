package com.example.securitytest.Controller;


import com.example.securitytest.Service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/mongo")
public class TestController {

    @Autowired
    TestService service;

    @GetMapping(value = "/find")
    public String findUserData(@RequestParam String name){
        return service.selectUser(name);
    }

    @GetMapping(value = "/save")
    public String saveUserData(@RequestParam String name,@RequestParam int age,@RequestParam String Email){
        log.info("[Controller][Recv] name: {name},age : {age},Email : {Email}",name,age,Email);
        service.saveUser(name,age,Email);

        return service.selectUser(name);
    }
}
