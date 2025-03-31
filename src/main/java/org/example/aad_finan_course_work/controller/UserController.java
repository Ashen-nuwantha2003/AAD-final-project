package org.example.aad_finan_course_work.controller;

import org.example.aad_finan_course_work.dto.AuthDTO;
import org.example.aad_finan_course_work.dto.ResponseDTO;
import org.example.aad_finan_course_work.dto.UserDTO;
import org.example.aad_finan_course_work.service.UserService;
import org.example.aad_finan_course_work.util.JwtUtil;
import org.example.aad_finan_course_work.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    // Constructor injection
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        List<UserDTO> dtos = userService.getAllUsers();
        System.out.println("awaa "+ dtos);
        ResponseDTO responseDTO = new ResponseDTO(VarList.Created, "Success", dtos);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }



    @GetMapping("/getUser")
    public ResponseEntity<ResponseDTO> getUser(@RequestParam String email) {
        UserDTO userDTO = userService.searchUser(email);
        System.out.println("ndsfisjdifsn"+userDTO.getUserId());
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(VarList.Not_Found, "User Not Found", null));
        }
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Success", userDTO));
    }

    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String email) {
        try {
            int result = userService.deleteUser(email);
            if (result == VarList.OK) {
                ResponseDTO responseDTO = new ResponseDTO(VarList.OK, "User deleted successfully", null);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else if (result == VarList.Not_Found) {
                ResponseDTO responseDTO = new ResponseDTO(VarList.Not_Found, "User not found", null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            } else {
                ResponseDTO responseDTO = new ResponseDTO(VarList.Bad_Gateway, "Error deleting user", null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO(VarList.Bad_Gateway, e.getMessage(), null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody  UserDTO userDTO) {
        System.out.println("Register");
        System.out.println("gggggggggggggggggggggggggggggggggggggggg"+userDTO.getEmail());
        System.out.println(userDTO.getRole());
        System.out.println(userDTO.getName());
        try {
            int res = userService.saveUser(userDTO);
            switch (res) {
                case VarList.Created: {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setRole(userDTO.getRole());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable: {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default: {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            // Log the exception (optional)
            e.printStackTrace();  // You can replace this with a proper logging mechanism
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Internal Server Error: " + e.getMessage(), null));
        }
    }

}