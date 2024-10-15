package cn.apkr.system.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2 {

    @RequestMapping("/hello2")
    public String hello2() {
        return "hello2";
    }
}
