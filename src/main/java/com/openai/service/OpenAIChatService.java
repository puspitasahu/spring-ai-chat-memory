package com.openai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OpenAIChatService{
    private final ChatClient chatClient;

    public OpenAIChatService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    //Message Roles:
    //User : User messages role(questions)
    //System :Rules,instruction
    //Assistant : AI Response
    //Tool : Function calling Input
    public String chatWithOpenAILLM(String message){
        ChatOptions chatOptions = ChatOptions.builder().model("gpt-4o-mini").temperature(0.3).maxTokens(500)
                //.frequencyPenalty(0.7)
                //  .presencePenalty(0.7)
                // .stopSequences(List.of("}"))
                //.topK(50)
                // .topP(0.5)
                .build();
        return chatClient.prompt(message).options(chatOptions).call().content();

    }
    public Flux<String> askToAIStream(String message){
        return chatClient.prompt(message)
                .stream()
                .content();

    }

}

