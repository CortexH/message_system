package com.Messaging_System.domain.service.user;

import com.Messaging_System.adapter.exception.CustomInternalException;
import com.Messaging_System.application.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
public class BusinessRuleApplicationService {

    private final UserRepositoryPort repository;

    public String findNonUsedNameTag(String name){
        List<String> usedTags = repository.findAllUsedTagsOfUsername(name);

        String recommendedTag = "";

        int iterations = 0;
        while(true){
            iterations++;
            String tag_final = String.valueOf(Math.round(Math.random() * 99999));
            StringBuilder tag = new StringBuilder(tag_final);

            for(int i = 0; i < (5 - tag_final.length()); i++){
                tag.insert(0, "0");
            }

            String tagst = tag.toString();

            if(!usedTags.contains(tagst)){
                recommendedTag = tagst;
                break;
            }

            if(iterations > 100){
                throw new CustomInternalException("Could not recommend any tag. Please, try again later or try your own tag.");
            }

        }

        return recommendedTag;
    }

    public String getUserTagFromFullName(String name){
        int start = name.length() - 5;
        return name.substring(start);
    }

    public String getUserNameFromFullName(String name){
        int tagIndex = name.length() - 6;
        return name.substring(0, tagIndex);

    }

}
