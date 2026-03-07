package com.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class ChatConfiguration {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, ChatMemory chatmemory){

        SimpleLoggerAdvisor loggerAdvisor = new SimpleLoggerAdvisor();

        Advisor memoryAdvisor = MessageChatMemoryAdvisor
                .builder(chatmemory)
                .build();

        return chatClientBuilder
                .defaultAdvisors(List.of(loggerAdvisor,memoryAdvisor))
                .build();
    }

}
