package com.openai.service;

import com.openai.dto.TripPlan;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TripPlannerService{

    private final ChatClient chatClient;

    @Value("classpath:Prompts/trip_guide_template.st")
    private Resource tripGuideTemplate;

    public TripPlannerService(final ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    public TripPlan getTripPlans(String message){
        return chatClient
                .prompt()
                .user(message)
               // .system(tripGuideTemplate)
                .call()
                // .entity(TripPlan.class);
                .entity(new BeanOutputConverter<>(TripPlan.class));
    }

    public List<String> getTripSpots(String message){
        return chatClient
                .prompt()
                .user(message)
                .call()
                .entity(new ListOutputConverter());
    }

    public Map<String,Object> getTripGuide(String message){
        return chatClient
                .prompt()
                .user(message)
                .call()
                .entity(new MapOutputConverter());
    }
    public List<TripPlan> completeTripPlans(String message){
        return chatClient
                .prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<List<TripPlan>>(){
                });
    }
}
