package org.example.aad_finan_course_work.service;

import org.example.aad_finan_course_work.dto.PackageDTO;
import org.example.aad_finan_course_work.dto.UserDTO;

import java.util.List;

public interface PackageService {
    int savePackage(PackageDTO packageDTO);
    List<PackageDTO> getAllpackages();
    void updatePackage(PackageDTO packageDTO);

}
