package com.example.artemisproducer.controllers;

import com.example.artemisproducer.ArtemisProducer;
import com.example.artemisproducer.models.APILogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/send")
public class RestApiController {
    @Autowired
    ArtemisProducer producer;

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE})
    public String produce(@RequestBody APILogEntry msg) {
        System.out.println(msg.toString());
        System.out.println(msg.getDumpRecords().getMsgs().size());
        producer.send(msg);
        return "Done";
    }
}