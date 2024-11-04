package org.example.service;

import org.example.model.enity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllFullName(String fullName);

    List<Teacher> getAllSubject(String subject);

    Integer updateFullNameAndSubject(Integer id, String fullname, String subject);

    void updateSalary(Integer id, double salary);

}
