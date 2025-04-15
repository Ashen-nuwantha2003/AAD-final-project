package org.example.aad_finan_course_work.controller;

import org.example.aad_finan_course_work.dto.ResponseDTO;
import org.example.aad_finan_course_work.dto.ServiceDTO;
import org.example.aad_finan_course_work.service.ServiceService;
import org.example.aad_finan_course_work.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/Service")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {this.serviceService = serviceService;}
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> saveService(@RequestBody @Validated ServiceDTO serviceDTO){
        int Result = serviceService.saveService(serviceDTO);
        ResponseDTO responseDTO = new ResponseDTO(VarList.Created, "Service Done", Result);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll() {
        List<ServiceDTO> dtos = serviceService.getAllservice();
        ResponseDTO responseDTO = new ResponseDTO(VarList.Created, "Success", dtos);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
