package org.example.aad_finan_course_work.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PackageDTO {

    private  int id;
    private String planname;
    private String description;
    private String validMonth;
    private double price;
}
