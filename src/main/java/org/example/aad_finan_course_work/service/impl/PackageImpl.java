package org.example.aad_finan_course_work.service.impl;


import jakarta.transaction.Transactional;
import org.example.aad_finan_course_work.dto.PackageDTO;
import org.example.aad_finan_course_work.dto.UserDTO;
import org.example.aad_finan_course_work.entity.Packages;
import org.example.aad_finan_course_work.entity.User;
import org.example.aad_finan_course_work.repo.PackageRepo;
import org.example.aad_finan_course_work.service.PackageService;
import org.example.aad_finan_course_work.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PackageImpl implements PackageService {
    @Autowired
    private PackageRepo packageRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public int savePackage(PackageDTO packageDTO) {
        packageRepo.save(modelMapper.map(packageDTO, Packages.class));
        return VarList.Created;
    }

    public List<PackageDTO> getAllpackages() {
        List<Packages> list = packageRepo.findAll();
        return list.stream()
                .map(packages -> modelMapper.map(packages,PackageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updatePackage(PackageDTO packageDTO) {
        if (!packageRepo.existsById(packageDTO.getId())) {
            throw new RuntimeException("Package does not exist!");
        }
        Packages packageEntity = modelMapper.map(packageDTO, Packages.class);
        packageRepo.save(packageEntity);
    }
}
