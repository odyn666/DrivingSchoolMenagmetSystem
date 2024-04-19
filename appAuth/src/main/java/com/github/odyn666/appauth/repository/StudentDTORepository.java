package com.github.odyn666.appauth.repository;

import com.github.odyn666.appauth.dto.StudentEntityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("STUDENT-SERVICE")
public interface StudentDTORepository  {
    @GetMapping(value = "/api/student/allDto")
    List<StudentEntityDto>getStudents();
}
