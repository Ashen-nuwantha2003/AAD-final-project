package org.example.aad_finan_course_work.service.impl;

import jakarta.transaction.Transactional;

import org.example.aad_finan_course_work.dto.UserDTO;
import org.example.aad_finan_course_work.entity.User;
import org.example.aad_finan_course_work.repo.UserRepo;
import org.example.aad_finan_course_work.service.UserService;
import org.example.aad_finan_course_work.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepo.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;  // Email already exists
        } else {
            // Hash the password
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encodedPassword);  // Set the encoded password


            // Save the user after mapping to the User entity
            userRepo.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;  // User successfully created
        }
    }

    @Override
    public UserDTO searchUser(String email) {
        if (userRepo.existsByEmail(email)) {
            User user = userRepo.findByEmail(email);
            return modelMapper.map(user, UserDTO.class);
        } else {
            return null;  // Return null if user doesn't exist
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> list = userRepo.findAll();
        return list.stream()
                .map(user -> modelMapper.map(user,UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public int deleteUser(String email) {
        if (userRepo.existsByEmail(email)) {
            userRepo.deleteByEmail(email);
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    public UserDTO loadUserDetailsByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return modelMapper.map(user, UserDTO.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }
}
