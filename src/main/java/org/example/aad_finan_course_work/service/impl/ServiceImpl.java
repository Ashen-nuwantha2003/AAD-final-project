package org.example.aad_finan_course_work.service.impl;


import jakarta.transaction.Transactional;
import org.example.aad_finan_course_work.dto.PackageDTO;
import org.example.aad_finan_course_work.dto.ServiceDTO;
import org.example.aad_finan_course_work.entity.Packages;
import org.example.aad_finan_course_work.entity.Services;
import org.example.aad_finan_course_work.repo.ServiceRepo;
import org.example.aad_finan_course_work.service.ServiceService;
import org.example.aad_finan_course_work.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepo serviceRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveService(ServiceDTO serviceDTO) {
        serviceRepo.save(modelMapper.map(serviceDTO, Services.class));
        return VarList.Created;
    }

    @Override
    public List<ServiceDTO> getAllservice() {
        List<Services> list = serviceRepo.findAll();
        return list.stream()
                .map(services -> modelMapper.map(services, ServiceDTO.class))
                .collect(Collectors.toList());
    }
}
