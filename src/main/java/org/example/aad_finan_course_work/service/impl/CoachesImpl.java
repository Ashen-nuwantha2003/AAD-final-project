package org.example.aad_finan_course_work.service.impl;

import jakarta.transaction.Transactional;
import org.example.aad_finan_course_work.dto.CoachesDTO;
import org.example.aad_finan_course_work.entity.Coaches;
import org.example.aad_finan_course_work.entity.Packages;
import org.example.aad_finan_course_work.repo.CoachesRepo;
import org.example.aad_finan_course_work.service.CoachesService;
import org.example.aad_finan_course_work.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoachesImpl implements CoachesService {
    @Autowired
    private CoachesRepo coachesRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveCoaches(CoachesDTO coachesDTO) {
        coachesRepo.save(modelMapper.map(coachesDTO, Coaches.class));
        System.out.println(coachesDTO);
        return VarList.Created;
    }
    public List<CoachesDTO> getAllcoaches() {
        List<Coaches> list = coachesRepo.findAll();
        return list.stream()
                .map(coaches -> modelMapper.map(coaches,CoachesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCoaches(CoachesDTO coachesDTO) {
        if (!coachesRepo.existsById(coachesDTO.getId())) {
            throw new RuntimeException("Offer does not exist!");
        }
        Coaches coachesEntity = modelMapper.map(coachesDTO, Coaches.class);
        coachesRepo.save(coachesEntity);
    }
}
