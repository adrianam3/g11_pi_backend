package com.hd.helpDesk.modules.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO {
    private List<Choice> choices;

    @Data
    public static class Choice {
        private MessageDTO message;
        private int index;
    }
}
