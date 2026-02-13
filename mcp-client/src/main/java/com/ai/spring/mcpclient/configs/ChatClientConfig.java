package com.ai.spring.mcpclient.configs;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    public ToolCallbackProvider mcpToolCallbackProvider(List<McpSyncClient> clients) {
        if (clients.isEmpty()) {
            throw new IllegalStateException(
                    "没有可用的 MCP 客户端！\n" +
                            "请检查：\n" +
                            "1. application.yml 中 spring.ai.mcp.client.enabled=true\n" +
                            "2. 已配置 stdio.connections 或 sse.connections\n" +
                            "3. 使用了正确的依赖 spring-ai-starter-mcp-client"
            );
        }
        return new SyncMcpToolCallbackProvider(clients.toArray(new McpSyncClient[0]));
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, ToolCallbackProvider mcpToolCallbackProvider) {
        return chatClientBuilder
                .defaultToolCallbacks(mcpToolCallbackProvider)
                .build();
    }
}