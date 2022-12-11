package com.ejada.artemisproducer.controllers;

import com.ejada.artemisproducer.ArtemisProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/send")
public class RestApiController {
    @Autowired
    ArtemisProducer producer;

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE})
    public String produce(@RequestBody String msg) {
        System.out.println(msg);
        producer.sendXMLStrimg(msg);
        return "Done";
    }
}