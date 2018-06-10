package com.daniel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Daniel on 2018/6/10.
 */
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @GetMapping("/auth/{code}")
    public void auth(@PathVariable(required = true) String code) {
        log.info("[weChat auth] code :{}", code);

    }
}
