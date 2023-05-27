package org.switch2022.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.TypologyDTO;
import org.switch2022.project.service.TypologyService;

@Controller
@RestController
@RequestMapping(path = "/typologies")

public class TypologyController {

    @Autowired
    private TypologyService typologyService;


    public TypologyController(TypologyService service) {
        this.typologyService = service;
    }

    /**
     * Endpoint to create a new typology
     *
     * @param typologyDto the object representing the typology to be created
     * @return ResponseEntity containing the created typology and http status code 201 - CREATED, if successful,
     * or a http status code 400 - BAD_REQUEST if the provided data is invalid
     */
    @PostMapping("")
    public ResponseEntity<TypologyDTO> createTypology(@RequestBody TypologyDTO typologyDto) {

        try {
            TypologyDTO savedTypologyDto = typologyService.createTypology(typologyDto);
            return new ResponseEntity<>(savedTypologyDto, HttpStatus.CREATED);

        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(typologyDto, HttpStatus.BAD_REQUEST);
        }
    }
}
