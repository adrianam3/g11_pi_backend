package com.hd.helpDesk.modules.tfaqsDetalles.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hd.helpDesk.modules.dto.MessageDTO;
import com.hd.helpDesk.modules.dto.RequestDTO;
import com.hd.helpDesk.modules.dto.ResponseDTO;
import com.hd.helpDesk.modules.modelos.TfaqsDetalle;
import com.hd.helpDesk.modules.openIA.service.OpenIAService;
import com.hd.helpDesk.modules.tfaqsDetalles.repository.TfaqsDetalleRepository;

@Service
public class TfaqsDetalleService {

    @Autowired
    private TfaqsDetalleRepository faqRepository;

    @Autowired
    private OpenIAService openAIService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    public List<TfaqsDetalle> getFaqs() {
        List<TfaqsDetalle> faqs = new ArrayList<>();
        faqs = this.faqRepository.findAll();
        return faqs;
    }

    public List<String> getRespuestas(List<String> palabrasClave) {
        List<String> respuestas = new ArrayList<>();
        respuestas = this.get(palabrasClave);
        return respuestas;
    }

    public String getValores(String query) {
        String dataFromDB = this.faqRepository.getSolucionFaq("%" + query+ "%");
        String enhancedPrompt = "Base de datos: " + dataFromDB + "\n\nConsulta del usuario: " + query;

        RequestDTO request = new RequestDTO(model, enhancedPrompt);
        ResponseDTO response = restTemplate.postForObject(apiUrl, request, ResponseDTO.class);
        @SuppressWarnings("null")
        MessageDTO respuesta = response.getChoices().get(0).getMessage();
        System.out.println(respuesta);
        return response.getChoices().get(0).getMessage().getContent();

        // RequestDTO requestDto = new RequestDTO(model, this.openAIService.getPrompt(enhancedPrompt));
        // ResponseDTO responseDto = restTemplate.postForObject(apiUrl, requestDto, ResponseDTO.class);
        // if (requestDto == null || responseDto.getChoices() == null ||
        // responseDto.getChoices().isEmpty())
        // return ResponseEntity.badRequest().body(new MessageDTO("user", "fallo en la
        // consulta"));

        // return responseDto.getChoices().get(0).getMessage();
    }

    private List<String> get(List<String> palabrasClave) {
        List<String> respuestas = new ArrayList<>();
        if (!palabrasClave.isEmpty() || palabrasClave != null) {
            for (String palabra : palabrasClave) {
                respuestas = this.faqRepository.getSolucionesFaq(palabra);
            }
        }
        return respuestas;
    }
}
