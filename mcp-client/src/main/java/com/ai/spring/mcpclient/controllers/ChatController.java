package com.ai.spring.mcpclient.controllers;

import com.ai.spring.mcpclient.service.DashScopeService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Resource
    private DashScopeService dashScopeService;

    @Resource
    private ChatClient chatClient;

    @PostMapping("/askServer")
    public String askServer(@RequestBody String userInput) {
        try {
            return chatClient.prompt(userInput)  // ✅ 直接使用 ChatClient
                    .call()
                    .content();
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    @PostMapping("/ask")
    public String chat(@RequestBody String userInput) {
        try {
            return dashScopeService.chat(userInput);
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    @PostMapping("/chatWithSystem")
    public String chatWithSystem(@RequestBody String userInput) {
        try {
            return dashScopeService.chatWithSystem("", userInput);
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    @PostMapping("/chatWithTools")
    public String chatWithTools(@RequestBody String userInput) {
        try {
            return dashScopeService.chatWithTools(userInput);
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    @PostMapping("/streamChat")
    public Flux<String> streamChat(@RequestBody String userInput) {
        try {
            return dashScopeService.streamChat(userInput);
        } catch (Exception e) {
            return Flux.error(e);
        }
    }
}