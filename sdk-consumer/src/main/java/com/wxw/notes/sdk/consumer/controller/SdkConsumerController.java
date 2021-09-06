package com.wxw.notes.sdk.consumer.controller;

import com.wxw.notes.sdk.provider.service.SdkProviderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxiongwei
 * @date 2021/9/6 10:09
 * @Description
 */
@RestController
public class SdkConsumerController {

    private final SdkProviderService sdkProviderService;

    public SdkConsumerController(SdkProviderService sdkProviderService) {
        this.sdkProviderService = sdkProviderService;
    }

    @RequestMapping("/sayHello")
    public String sayHello(){
        return sdkProviderService.sayHello();
    }
}
