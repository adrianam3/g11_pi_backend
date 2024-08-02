package com.hd.helpDesk.modules.openIA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hd.helpDesk.modules.dto.MessageDTO;
import com.hd.helpDesk.modules.dto.RequestDTO;
import com.hd.helpDesk.modules.dto.ResponseDTO;

@Service
public class OpenIAService {

    @Value("${openai.model}")
    private String model;
    @Value("${openai.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;


//lamada api sin condicinante ¿, recibe texto y devuelve texto 
    @SuppressWarnings("null")
    public String callOpenAIByPrompt(String prompt) {
        String respuesta = "";
        RequestDTO request = new RequestDTO(model, prompt);
        ResponseDTO response = restTemplate.postForObject(apiUrl, request, ResponseDTO.class);
        System.out.println(response.getChoices().get(0).getMessage());
        respuesta = response.getChoices().get(0).getMessage().getContent();
        return respuesta;
    }


//llamada de api condicinando respuesta en el promt , devuelve json 


    @SuppressWarnings("null")
    public MessageDTO getRespuesta(String prompt) {
        RequestDTO requestDto = new RequestDTO(model, this.getPrompt(prompt));
        ResponseDTO responseDto = restTemplate.postForObject(apiUrl, requestDto, ResponseDTO.class);
        if (requestDto == null || responseDto.getChoices() == null || responseDto.getChoices().isEmpty())
            return new MessageDTO("user", "falló en la consulta");
        return responseDto.getChoices().get(0).getMessage();
    }

    private String getPrompt(String consulta) {
        String prompt = " Presenta la mejor opción de respuesta para la consulta : " + consulta
                + " \n  considerando que el tópico tratado es soporte de software.";
        return prompt;
    }
}
