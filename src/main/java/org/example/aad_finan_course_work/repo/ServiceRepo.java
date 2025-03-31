package org.example.aad_finan_course_work.repo;


import org.example.aad_finan_course_work.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<Services,Integer> {
}
