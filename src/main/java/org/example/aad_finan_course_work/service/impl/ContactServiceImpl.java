package org.example.aad_finan_course_work.service.impl;


import jakarta.transaction.Transactional;
import org.example.aad_finan_course_work.dto.ContactDTO;
import org.example.aad_finan_course_work.entity.Contact;
import org.example.aad_finan_course_work.repo.ContactRepo;
import org.example.aad_finan_course_work.service.ContactService;
import org.example.aad_finan_course_work.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public int save(ContactDTO contactDTO) {
    if(ContactRepo.existsByEmail(contactDTO.getEmail())){
        return VarList.Not_Acceptable;
    }else{
        contactRepo.save(modelMapper.map(contactDTO, Contact.class));
        return VarList.Created;
    }
    }



}
