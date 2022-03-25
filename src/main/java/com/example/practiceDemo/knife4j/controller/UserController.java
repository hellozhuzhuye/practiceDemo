package com.example.practiceDemo.knife4j.controller;

import com.example.practiceDemo.knife4j.config.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 启动后访问 127.0.0.1:9090/doc.html
 * knife4j快速开始 https://doc.xiaominfo.com/knife4j/documentation/get_start.html
 */
@Api(tags = "knife4j测试")
@RestController
@RequestMapping("/knife4j")
public class UserController {

    @ApiImplicitParam(name = "name",value = "姓名",required = true)
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name")String name){
        return ResponseEntity.ok("Hi:"+name);
    }

    @ApiOperation(value = "获取user")
    @PostMapping("/getUser")
    public ResponseEntity<String> sayHi(@RequestBody User user){
        return ResponseEntity.ok(user.toString());
    }
}
