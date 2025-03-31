package org.example.aad_finan_course_work.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment {
    @Id
    private int id;
    private int orderid;
    private int userid;
    private double paymentamount;
    private Date paymentdate;
    private String paymentmethod;
}
