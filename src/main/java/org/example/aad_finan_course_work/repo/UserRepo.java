package org.example.aad_finan_course_work.repo;


import org.example.aad_finan_course_work.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

    User findByEmail(String email);  // Find user by email
    boolean existsByEmail(String email);  // Check if email exists
    int deleteByEmail(String email);  // Delete user by email (optional)

}
