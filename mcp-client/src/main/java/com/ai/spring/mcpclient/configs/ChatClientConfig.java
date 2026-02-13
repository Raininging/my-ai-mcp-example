package com.ai.spring.mcpclient.configs;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Resource
    private MyTools myTools;
    // 1. 扫描 @Tool 注解，生成 ToolCallbackProvider
    @Bean
    public ToolCallbackProvider toolCallbackProvider() {
        return MethodToolCallbackProvider.builder()
                .toolObjects(myTools)  // 传入带 @Tool 方法的 Bean
                .build();
    }

    /**
     * 2. 将 MCP Client 包装成 ToolCallbackProvider
     * ⚠️ 这就是你之前缺失的 Bean！
     */
//    @Bean
//    public ToolCallbackProvider mcpToolCallbackProvider(McpSyncClient mcpSyncClient) {
//        return new SyncMcpToolCallbackProvider(mcpSyncClient);
//    }

    /**
     * Creates and configures a `ChatClient` bean.
     *
     * @param chatClientBuilder the builder used to construct the `ChatClient`
     * @return a fully built and configured `ChatClient` instance
     */
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, ToolCallbackProvider toolCallbackProvider) {
        return chatClientBuilder
//                .defaultToolCallbacks(toolCallbackProvider.getToolCallbacks())
                  .defaultTools(myTools)
                  .build();
    }
}