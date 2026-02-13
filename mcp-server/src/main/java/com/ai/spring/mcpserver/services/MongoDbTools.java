package com.ai.spring.mcpserver.services;

import com.mongodb.client.MongoClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MongoDbTools {
    private final MongoClient mongoClient;

    public MongoDbTools(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        System.out.println("✅ MongoDB工具初始化成功！");
    }


    @Tool(description = "获取MongoDB指定集合的文档总数")
    public String countDocuments(
            @ToolParam(description = "数据库名称") String database,
            @ToolParam(description = "集合名称") String collection) {
        try {
            var db = mongoClient.getDatabase(database);
            var coll = db.getCollection(collection);
            long count = coll.countDocuments();
            return String.format("📊 数据库 `%s` 的集合 `%s` 共有 **%,d** 条文档",
                    database, collection, count);
        } catch (Exception e) {
            return "❌ 查询失败: " + e.getMessage();
        }
    }

    @Tool(description = "获取MongoDB所有数据库列表")
    public String listDatabases() {
        try {
            var databases = mongoClient.listDatabaseNames().into(new ArrayList<>());
            var result = new StringBuilder("📚 MongoDB数据库列表：\n");
            for (String dbName : databases) {
                result.append("• ").append(dbName).append("\n");
            }
            return result.toString();
        } catch (Exception e) {
            return "❌ 查询失败: " + e.getMessage();
        }
    }
}
