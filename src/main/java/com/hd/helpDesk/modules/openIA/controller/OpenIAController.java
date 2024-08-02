package com.hd.helpDesk.modules.openIA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hd.helpDesk.modules.dto.MessageDTO;
import com.hd.helpDesk.modules.openIA.service.OpenIAService;

@RestController
@CrossOrigin
public class OpenIAController {

    @Autowired
    private OpenIAService openIAService;

    @PostMapping("/chat")
    public ResponseEntity<MessageDTO> chat(@RequestBody String prompt) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO = this.openIAService.getRespuesta(prompt);

        return ResponseEntity.ok(messageDTO);
    }

    @GetMapping("/getchat")
    public String pruebaChat(@RequestParam("prompt") String prompt) {
        String respuesta = this.openIAService.callOpenAIByPrompt(prompt);
        return respuesta;
    }
}
