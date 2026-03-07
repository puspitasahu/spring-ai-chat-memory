package com.openai.controller;

import com.openai.service.OpenAIChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/openai/api")
public class OpenAIChatController{
    private final OpenAIChatService openAIChatService;

    public OpenAIChatController(final OpenAIChatService openAIChatService){
        this.openAIChatService = openAIChatService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message,@RequestParam String userName){
        return openAIChatService.askToAIStream(message,userName);
    }

}

