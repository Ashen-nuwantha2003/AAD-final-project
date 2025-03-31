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
public class Packages {
  @Id
  private  int id;
  private String planname;
  private String description;
  private String validMonth;
  private double price;
}
