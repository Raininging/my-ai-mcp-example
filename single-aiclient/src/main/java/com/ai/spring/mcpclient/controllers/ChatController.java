package com.ai.spring.mcpclient.controllers;

import com.ai.spring.mcpclient.service.DashScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final DashScopeService dashScopeService;

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