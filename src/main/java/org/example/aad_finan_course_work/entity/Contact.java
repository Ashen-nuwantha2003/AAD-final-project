package org.example.aad_finan_course_work.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "contact")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    private String name;
    private String email;
    private String subject;
    private String message;

}
