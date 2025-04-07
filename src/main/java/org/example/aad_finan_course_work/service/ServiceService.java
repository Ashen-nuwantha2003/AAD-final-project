package org.example.aad_finan_course_work.service;

import org.example.aad_finan_course_work.dto.PackageDTO;
import org.example.aad_finan_course_work.dto.ServiceDTO;

import java.util.List;

public interface ServiceService {
    int saveService(ServiceDTO serviceDTO);

    List<ServiceDTO> getAllservice();
}
