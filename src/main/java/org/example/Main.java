package org.example;

import org.example.model.enity.Student;
import org.example.serviceImpl.StudentServiceImpl;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {


       //TeacherServiceImpl teacherService=new TeacherServiceImpl();
        StudentServiceImpl studentService=new StudentServiceImpl();
       Student student=new Student();


       // Student student=new Student(12,"Benovse",22,"benovse","23232342","jsjasjja",3.5,CoursYear.ONE);
        //System.out.println(studentService.create(student));

       // System.out.println(studentService.delete(1));




    }}

