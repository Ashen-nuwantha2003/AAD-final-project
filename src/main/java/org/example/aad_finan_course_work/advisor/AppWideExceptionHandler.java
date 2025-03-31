package org.example.aad_finan_course_work.advisor;


import org.example.aad_finan_course_work.util.ResponseUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin
public class AppWideExceptionHandler {
    public ResponseUtil exceptionHandler(Exception ex){
        return new org.example.aad_finan_course_work.util.ResponseUtil(500,ex.getMessage(),null);
    }
}
