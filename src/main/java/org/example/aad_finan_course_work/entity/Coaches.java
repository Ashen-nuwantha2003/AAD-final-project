package org.example.aad_finan_course_work.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Coaches {
    @Id
    private int id;
    private  String name;
    private String email;
    private String experience;
    private String imagePath;
    private String price;

}
