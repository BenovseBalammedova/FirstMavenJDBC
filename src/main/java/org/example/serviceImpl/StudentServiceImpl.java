package org.example.serviceImpl;

import org.example.database.DatabaseConnection;
import org.example.model.enity.Student;
import org.example.model.enums.CoursYear;
import org.example.service.CommonService;
import org.example.service.StudentService;
import org.example.util.FileWrite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements CommonService<Student>, StudentService {
    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Student";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String fullName = resultSet.getString(2);
                Integer age = resultSet.getInt(3);
                String email = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);
                String major = resultSet.getString(6);
                double gpa = resultSet.getDouble(7);
                String coursYear = resultSet.getString(8);
                CoursYear coursYears = CoursYear.valueOf(coursYear);
                Student student = new Student(id, fullName, age, email, phoneNumber, major, gpa, coursYears);
                students.add(student);

            }
            stmt.close();
            resultSet.close();

        } catch (SQLException | NullPointerException ex) {
            ex.printStackTrace();
            throw new NullPointerException("There is nothing information");
        }
        if (!students.isEmpty()) {
            FileWrite.fileWriteFromService("alluser.txt", students.toString());
        }

        return students;

    }

    @Override
    public Student create(Student user) {
        String query = "Insert Into Student values(?,?,?,?,?,?,?,?)";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getFullName());
            stmt.setInt(3, user.getAge());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setString(6, user.getMajor());
            stmt.setDouble(7, user.getGpa());
            stmt.setString(8, user.getCoursYear().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("You are not added illegal argument!!!");
        }
        FileWrite.fileWriteFromService("createuser", user.toString());
        return user;
    }

    @Override
    public Integer delete(Integer id) {
        String query = "delete from Student where id=?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        FileWrite.fileWriteFromService("deleteuser.txt", id.toString() + "th element deleted");
        return id;
    }

    @Override
    public Student update(Integer id, Student user) {
        String query = "Update Student set fullName = ?,age = ?,email = ?,phoneNumber = ?,major = ?,gpa = ?,coursYear = ? WHERE id = ?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getFullName());
            stmt.setInt(3, user.getAge());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setString(6, user.getMajor());
            stmt.setDouble(7, user.getGpa());
            stmt.setString(8, user.getCoursYear().name());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FileWrite.fileWriteFromService("updateuser.txt", user.toString());
        return user;
    }

    @Override
    public Student getById(Integer id) {
        String query = "Select*from Student where id=?";
        Connection connection = DatabaseConnection.getConnection();
        Student student = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                student = new Student(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7),
                        CoursYear.valueOf(resultSet.getString(8))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        FileWrite.fileWriteFromService("getbyiduser.txt", id.toString());
        return student;
    }

    @Override
    public List<Student> getAllFullName(String fullName) {
        List<Student> students = new ArrayList<>();
        String query = "Select *from Student where full_name =?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, fullName);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String fullNames = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String email = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);
                String major = resultSet.getString(6);
                double gpa = resultSet.getDouble(7);
                String coursYear = resultSet.getString(8);
                CoursYear coursYears = CoursYear.valueOf(coursYear);
                Student student = new Student(id, fullNames, age, email, phoneNumber, major, gpa, coursYears);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        students = students.stream().filter(student -> student.getFullName().equals(fullName)).toList();
        FileWrite.fileWriteFromService("FullName.txt", students.toString());
        return students;
    }

    @Override
    public List<Student> getAllMajor(String major) {
        List<Student> students = new ArrayList<>();
        String query = "Select *from Student where major=?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, major);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String fullNames = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String email = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);
                String majors = resultSet.getString(6);
                double gpa = resultSet.getDouble(7);
                String coursYear = resultSet.getString(8);
                CoursYear coursYears = CoursYear.valueOf(coursYear);
                Student student = new Student(id, fullNames, age, email, phoneNumber, majors, gpa, coursYears);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        students = students.stream().filter(student -> student.getMajor().equals(major)).toList();
        FileWrite.fileWriteFromService("allmajor.txt", students.toString());
        return students;
    }

    @Override
    public Integer updateFullNameAndMajor(Integer id, String fullname, String major) {

        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "Update Student set full_name=?,major=? where id=?");
            stmt.setString(1, fullname);
            stmt.setString(2, major);
            stmt.setInt(3, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FileWrite.fileWriteFromService("nameandmajor.txt", id.toString());
        return id;
    }

    @Override

    public void updateGpa(Integer id, double gpa) {
        String query = "Update Student set gpa=? where id=?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDouble(1, gpa);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        FileWrite.fileWriteFromService("updategpa.txt", id.toString());
    }
}
