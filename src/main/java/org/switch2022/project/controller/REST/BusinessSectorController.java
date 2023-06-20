package org.switch2022.project.controller.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.*;
import org.switch2022.project.service.BusinessSectorService;

import java.util.ArrayList;

@Controller
@RestController
@RequestMapping(path = "/businessSectors")
public class BusinessSectorController {
    @Autowired
    BusinessSectorService businessSectorService;

    @Autowired
    BusinessSectorMapper businessSectorMapper;

    public BusinessSectorController (BusinessSectorService businessSectorService) {
        this.businessSectorService = businessSectorService;
    }

    /**
     * Post method responsible for creating a businessSector.
     * @param businessSector (dto)
     * @return when successful, the status http 201 (created) is returned, or otherwise 400 (bad request)
     */
    @PostMapping("")
    public ResponseEntity<BusinessSectorOutputDTO> createBusinessSector(@RequestBody BusinessSectorDTO businessSector) {

        try {
            BusinessSectorDTO savedBusinessSector = businessSectorService.createBusinessSector(businessSector);
            BusinessSectorOutputDTO businessSectorOutputDTO = businessSectorMapper.toOutputDTO(savedBusinessSector);
            return new ResponseEntity<>(businessSectorOutputDTO, HttpStatus.CREATED);
        }
        catch (InvalidDataAccessApiUsageException exception) {
            BusinessSectorOutputDTO businessSectorOutputDTO = businessSectorMapper.toOutputDTO(businessSector);
            return new ResponseEntity<>(businessSectorOutputDTO, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Post method responsible for return all Business Sectors.
     * @return when successful, the status http 200 (created) and a ArrayList of BusinessSectorOutputDTO is returned, or otherwise 400 (bad request)
     */
    @GetMapping("")
    public ResponseEntity<ArrayList<BusinessSectorOutputDTO>> getAll() {

        ArrayList<BusinessSectorOutputDTO> businessSectorOutput = new ArrayList();

        try {
            ArrayList<BusinessSectorDTO> businessSectorsOutput = businessSectorService.getAll();
            businessSectorOutput =  businessSectorMapper.toOutputDTO(businessSectorsOutput);

            return new ResponseEntity<>(businessSectorOutput, HttpStatus.OK);
        }
        catch (InvalidDataAccessApiUsageException exception){
            return new ResponseEntity<>(businessSectorOutput, HttpStatus.BAD_REQUEST);
        }
    }
}
