package com.github.odyn666.appauth.service;

import com.github.odyn666.appauth.dto.StudentEntityDto;
import com.github.odyn666.appauth.repository.StudentDTORepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentDTOService {
    private final StudentDTORepository studentDTORepository;

    public List<StudentEntityDto> getStudents() {
        return studentDTORepository.getStudents();
    }
}
