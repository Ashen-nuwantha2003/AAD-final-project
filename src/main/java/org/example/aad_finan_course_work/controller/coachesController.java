package org.example.aad_finan_course_work.controller;


import org.example.aad_finan_course_work.dto.CoachesDTO;
import org.example.aad_finan_course_work.dto.PackageDTO;
import org.example.aad_finan_course_work.dto.ResponseDTO;
import org.example.aad_finan_course_work.service.CoachesService;
import org.example.aad_finan_course_work.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/coaches")
public class coachesController {
    private final CoachesService coachesService;

    public coachesController(CoachesService coachesService) {
        this.coachesService = coachesService;
    }
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> saveCoaches(@RequestBody @Validated CoachesDTO coachesDTO){
        int Result = coachesService.saveCoaches(coachesDTO);
        ResponseDTO responseDTO = new ResponseDTO(VarList.Created,"Coaches Register Successfully", Result);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllcoaches() {
        List<CoachesDTO> dtos = coachesService.getAllcoaches();
        System.out.println("awaa "+ dtos);
        ResponseDTO responseDTO = new ResponseDTO(VarList.Created, "Success", dtos);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
