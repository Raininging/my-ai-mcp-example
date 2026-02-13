//package com.ai.spring.mcpclient.configs;
//
//import io.modelcontextprotocol.client.McpClient;
//import io.modelcontextprotocol.client.McpSyncClient;
//import io.modelcontextprotocol.client.transport.StdioClientTransport;
//import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
//import org.springframework.ai.tool.ToolCallbackProvider;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class McpClientConfig {
//    @Bean(destroyMethod = "close")
//    public McpSyncClient mcpSyncClient() {
//        // 硬编码，完全绕过配置文件
//        ProcessBuilder processBuilder = new ProcessBuilder(
//                "npx",
//                "-y",
//                "@modelcontextprotocol/server-everything"
//        );
//        processBuilder.redirectErrorStream(true);
//
//        StdioClientTransport transport = new StdioClientTransport(processBuilder);
//        return McpClient.sync(transport).build();
//    }
//
//    @Bean
//    public ToolCallbackProvider mcpToolCallbackProvider(McpSyncClient mcpSyncClient) {
//        return new SyncMcpToolCallbackProvider(mcpSyncClient);
//    }
//}
