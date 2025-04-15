package org.example.aad_finan_course_work.controller;


import org.example.aad_finan_course_work.dto.PackageDTO;
import org.example.aad_finan_course_work.dto.ResponseDTO;
import org.example.aad_finan_course_work.service.PackageService;
import org.example.aad_finan_course_work.util.ResponseUtil;
import org.example.aad_finan_course_work.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/packages")
public class PackagesController {
    private final PackageService packageService;

    public PackagesController(PackageService packageService) {this.packageService = packageService;}
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> savePackage(@RequestBody @Validated PackageDTO packageDTO){
        int Result = packageService.savePackage(packageDTO);
        ResponseDTO responseDTO = new ResponseDTO(VarList.Created,"Package Active Successfully", Result);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllpackages() {
        List<PackageDTO> dtos = packageService.getAllpackages();
        ResponseDTO responseDTO = new ResponseDTO(VarList.Created, "Success", dtos);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseUtil updatePackage(@PathVariable String id, @RequestBody PackageDTO packageDTO) {
        packageDTO.setId(Integer.parseInt(id));
        packageService.updatePackage(packageDTO);
        return new ResponseUtil(200, "Package updated successfully", null);
    }

}
