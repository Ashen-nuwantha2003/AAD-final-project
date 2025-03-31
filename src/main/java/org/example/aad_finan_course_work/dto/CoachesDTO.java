package org.example.aad_finan_course_work.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CoachesDTO {

    private int id;
    private  String name;
    private String email;
    private String experience;
    private String imagePath;
    private String price;
}
