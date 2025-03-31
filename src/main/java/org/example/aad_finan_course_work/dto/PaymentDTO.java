package org.example.aad_finan_course_work.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentDTO {
    private int id;
    private int orderid;
    private int userid;
    private double paymentamount;
    private Date paymentdate;
    private String paymentmethod;
}
