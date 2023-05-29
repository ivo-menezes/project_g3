package org.switch2022.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.switch2022.project.mapper.BusinessSectorDTO;
import org.switch2022.project.service.BusinessSectorService;

@Controller
@RestController
@RequestMapping(path = "/businessSector")
public class BusinessSectorController {
    @Autowired
    BusinessSectorService businessSectorService;

    public BusinessSectorController (BusinessSectorService businessSectorService) {
        this.businessSectorService = businessSectorService;
    }

    /**
     * Post method responsible for creating a businessSector.
     * @param businessSector (dto)
     * @return when successful, the status http 201 (created) is returned, or otherwise 400 (bad request)
     */
    @PostMapping("")
    public ResponseEntity<BusinessSectorDTO> createBusinessSector(@RequestBody BusinessSectorDTO businessSector) {

        try {
            BusinessSectorDTO savedBusinessSector = businessSectorService.createBusinessSector(businessSector);
            return new ResponseEntity<>(savedBusinessSector, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(businessSector, HttpStatus.BAD_REQUEST);
        }
    }
}
