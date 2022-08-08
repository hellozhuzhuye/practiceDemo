package com.example.practiceDemo.rabbitmq;

import cn.hutool.core.thread.ThreadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "RabbitController", description = "RabbitMQ功能测试")
@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private SimpleSender simpleSender;

    @ApiOperation("简单模式")
    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    public String simpleTest() {
        for(int i=0;i<10;i++){
            simpleSender.send("hello"+i);
            ThreadUtil.sleep(1000);
        }
        return "success";
    }
}