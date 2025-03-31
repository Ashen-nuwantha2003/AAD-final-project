package org.example.aad_finan_course_work.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ServiceDTO {
    private int id;
    private String name;
    private String description;
    private String imagepath;
}
