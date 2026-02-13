package com.ai.spring.mcpclient.configs;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class MyTools {
    @Tool(description = "获取当前时间")
    public String getCurrentTime() {
        return LocalTime.now().toString();
    }
}
