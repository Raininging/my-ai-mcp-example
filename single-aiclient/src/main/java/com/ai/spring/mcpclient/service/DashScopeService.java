package com.ai.spring.mcpclient.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * 你的 Service
 *     → 调用 ChatClient (Spring AI 管理)
 *         → 调用 DashScopeChatModel (Starter 自动配置)
 *             → 调用 RestClient/WebClient (Starter 自动配置)
 *                 → 调用 DashScope API
 */
@Service
public class DashScopeService {

    private final ChatClient chatClient;  // ✅ 改为 ChatClient

    public DashScopeService(ChatClient chatClient) {  // ✅ 注入 ChatClient
        this.chatClient = chatClient;
    }

    public String chat(String message) {
        return chatClient.prompt(message)  // ✅ 直接使用 ChatClient
                .call()
                .content();
    }

    // 流式调用
    public Flux<String> streamChat(String message) {
        return chatClient.prompt(message)
                .stream()
                .content();
    }

    // 带系统提示词
    public String chatWithSystem(String system, String user) {
        return chatClient.prompt()
                .system(system)
                .user(user)
                .call()
                .content();
    }

    // 带工具调用
    public String chatWithTools(String message, ToolCallback... tools) {
        return chatClient.prompt(message)
                .tools(tools)  // 或 .toolCallbacks(tools)
                .call()
                .content();
    }
}