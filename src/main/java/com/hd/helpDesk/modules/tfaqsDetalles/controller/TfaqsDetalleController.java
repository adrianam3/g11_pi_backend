package com.hd.helpDesk.modules.tfaqsDetalles.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hd.helpDesk.modules.dto.MessageDTO;
import com.hd.helpDesk.modules.modelos.TfaqsDetalle;
import com.hd.helpDesk.modules.services.ErrorService;
import com.hd.helpDesk.modules.services.HttpResponseService;
import com.hd.helpDesk.modules.tfaqsDetalles.service.TfaqsDetalleService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/faq")
public class TfaqsDetalleController {
    @Autowired
    private TfaqsDetalleService faqService;

    @GetMapping("/")
    public ResponseEntity<?> getGaqs() {
        try {
            List<String> palabrasClave = new ArrayList<>();
            palabrasClave.add("impresora");
            palabrasClave.add("imprime");
            List<String> faqs = this.faqService.getRespuestas(palabrasClave);
            return HttpResponseService.responseOK(faqs);
        } catch (Exception e) {
            String accion = "Get faqs";
            log.error(accion, e);
            return HttpResponseService.responseInternalError(new ErrorService(e.getMessage(), accion, e));
        }
    }

    @GetMapping("/query")
    public String getResponse(@RequestParam String query) {
        return this.faqService.getValores(query);
    }

    @GetMapping(value = "/hola")
    public String holaMundo() {
        return "Hola Mundo!";
    }
}
