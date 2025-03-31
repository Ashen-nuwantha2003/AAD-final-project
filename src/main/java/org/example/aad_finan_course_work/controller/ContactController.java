package org.example.aad_finan_course_work.controller;

import org.example.aad_finan_course_work.dto.AuthDTO;
import org.example.aad_finan_course_work.dto.ContactDTO;
import org.example.aad_finan_course_work.dto.ResponseDTO;
import org.example.aad_finan_course_work.dto.UserDTO;
import org.example.aad_finan_course_work.service.ContactService;
import org.example.aad_finan_course_work.util.JwtUtil;
import org.example.aad_finan_course_work.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/contact")
public class ContactController {
@Autowired
    private ContactService contactService;

    @PostMapping("/saveMasage")
    public ResponseEntity<ResponseDTO> saveContact(@RequestBody ContactDTO contactDTO) {
        System.out.println("Saveeeee");
        try{
            int res = contactService.save(contactDTO);
            switch (res) {
                case VarList.Created: {
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", contactDTO));
                }
                case VarList.Not_Acceptable: {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Id already Use", null));
                }
                default: {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    }
