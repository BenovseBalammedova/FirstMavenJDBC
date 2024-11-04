package org.example.service;

import org.example.model.enity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllFullName(String fullName);

    List<Student> getAllMajor(String major);

    Integer updateFullNameAndMajor(Integer id,String fullname,String major);

    void updateGpa(Integer id,double gpa);
}
