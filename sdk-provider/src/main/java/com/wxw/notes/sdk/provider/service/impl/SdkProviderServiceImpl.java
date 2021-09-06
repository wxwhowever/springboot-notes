package com.wxw.notes.sdk.provider.service.impl;

import com.wxw.notes.sdk.provider.service.SdkProviderService;
import org.springframework.stereotype.Service;

/**
 * @author wuxiongwei
 * @date 2021/9/6 10:12
 * @Description
 */
@Service
public class SdkProviderServiceImpl implements SdkProviderService {
    @Override
    public String sayHello() {
        return "hello I'm Sdk Provider";
    }
}
