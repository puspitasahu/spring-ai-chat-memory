package com.openai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@Service
public class OpenAIChatService{
    private final ChatClient chatClient;
    public OpenAIChatService(ChatClient chatClient){
        this.chatClient = chatClient;
    }
    public String askToAIStream(String message, String userName){
        return chatClient.prompt(message)
                .advisors(adviceSpec-> adviceSpec.param(CONVERSATION_ID,userName))
                .call()
                .content();

    }
}

