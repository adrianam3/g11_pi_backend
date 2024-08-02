package com.hd.helpDesk.modules.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RequestDTO {

    private String model;
    private List<MessageDTO> messages;

    public RequestDTO(String model, String prompt) {
        this.model = model;
        messages = new ArrayList<>();
        messages.add(new MessageDTO("user", prompt));
    }
}