package org.example.aad_finan_course_work.repo;

import org.example.aad_finan_course_work.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contact,Integer> {

    static boolean existsByEmail(String email) {
        return false;
    }

    static List<Contact> findByEmail(String email) {
        return null;
    }

    // Custom query to find contacts by name (case-insensitive)
    List<Contact> findByNameIgnoreCase(String name);
}
