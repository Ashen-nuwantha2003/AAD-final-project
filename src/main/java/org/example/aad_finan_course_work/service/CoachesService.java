package org.example.aad_finan_course_work.service;


import org.example.aad_finan_course_work.dto.CoachesDTO;

import java.util.List;

public interface CoachesService {
    int saveCoaches(CoachesDTO coachesDTO);
    List<CoachesDTO> getAllcoaches();
    void updateCoaches(CoachesDTO coachesDTO);
}
