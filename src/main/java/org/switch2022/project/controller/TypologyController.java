package org.switch2022.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.TypologyDTO;
import org.switch2022.project.mapper.TypologyMapper;
import org.switch2022.project.mapper.TypologyOutputDTO;
import org.switch2022.project.service.TypologyService;

@Controller
@RestController
@RequestMapping(path = "/typologies")

public class TypologyController {

    @Autowired
    private TypologyService typologyService;

    @Autowired
    private TypologyMapper typologyMapper;


    public TypologyController(TypologyService service) {
        this.typologyService = service;
    }

    /**
     * Endpoint to create a new typology
     *
     * @param typology the object representing the typology to be created
     * @return ResponseEntity containing the created typology and http status code 201 - CREATED, if successful,
     * or a http status code 400 - BAD_REQUEST if the provided data is invalid
     */
    @PostMapping("")
    public ResponseEntity<TypologyOutputDTO> createTypology(@RequestBody TypologyDTO typology) {

        try {
            TypologyDTO savedTypologyDto = typologyService.createTypology(typology);
            TypologyOutputDTO typologyOutputDTO =  typologyMapper.toOutputDTO(savedTypologyDto);
            return new ResponseEntity<>(typologyOutputDTO, HttpStatus.CREATED);

        } catch (InvalidDataAccessApiUsageException exception) {
            TypologyOutputDTO typologyOutputDTO =  typologyMapper.toOutputDTO(typology);
            return new ResponseEntity<>(typologyOutputDTO, HttpStatus.BAD_REQUEST);
        }
    }
}
