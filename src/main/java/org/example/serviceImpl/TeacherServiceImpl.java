package org.example.serviceImpl;

import org.example.database.DatabaseConnection;
import org.example.model.enity.Teacher;

import org.example.model.enums.Position;
import org.example.service.CommonService;
import org.example.service.TeacherService;
import org.example.util.FileWrite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements CommonService<Teacher>, TeacherService {
    @Override
    public List<Teacher> getAll() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "Select * from Teacher";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = new Teacher(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDouble(6),
                        resultSet.getString(7),
                        Position.valueOf(resultSet.getString(8))
                );

                teachers.add(teacher);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException ex) {
            throw new NullPointerException("There is nothing information");
        }
        if (!teachers.isEmpty()) {
            FileWrite.fileWriteFromService("alluser.txt", teachers.toString());
        }
        return teachers;
    }

    @Override
    public Teacher create(Teacher user) {
        String query = "Insert into Teacher values(?,?,?,?,?,?,?,?)";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getFullName());
            stmt.setInt(3, user.getAge());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setDouble(6, user.getSalary());
            stmt.setString(7, user.getSubject());
            stmt.setString(8, user.getPosition().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("You are not added illegal argument!!!");
        }
        FileWrite.fileWriteFromService("createuser.txt", user.toString());

        return user;
    }

    @Override
    public Integer delete(Integer id) {
        String query = "delete from teacher where id=?";
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
    public Teacher update(Integer id, Teacher user) {
        String query = "Update Teacher set fullName = ?,age = ?,email = ?,phoneNumber = ?,salary=?,subject=?,position=? where id=?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getFullName());
            stmt.setInt(3, user.getAge());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setDouble(6, user.getSalary());
            stmt.setString(7, user.getSubject());
            stmt.setString(8, user.getPosition().name());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FileWrite.fileWriteFromService("updateuser.txt", user.toString());
        return user;
    }

    @Override
    public Teacher getById(Integer id) {
        String query = "Select *from Teacher where id=?";
        Connection connection = DatabaseConnection.getConnection();
        Teacher teacher = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                teacher = new Teacher(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDouble(6),
                        resultSet.getString(7),
                        Position.valueOf(resultSet.getString(8)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FileWrite.fileWriteFromService("getbyiduser.txt", teacher.toString());
        return teacher;
    }

    @Override
    public List<Teacher> getAllFullName(String fullName) {
        List<Teacher> teachers = new ArrayList<>();
        String query = "Select *from Teacher where full_name=?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, fullName);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = new Teacher(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDouble(6),
                        resultSet.getString(7),
                        Position.valueOf(resultSet.getString(8))

                );
                teachers.add(teacher);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        teachers = teachers.stream().filter(teacher -> teacher.getFullName().equals(fullName)).toList();
        FileWrite.fileWriteFromService("FullName.txt", teachers.toString());
        return teachers;
    }

    @Override
    public List<Teacher> getAllSubject(String subject) {
        List<Teacher> teachers = new ArrayList<>();
        String query = "Select *from Teacher where subject=?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, subject);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String fullName = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String email = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);
                double salary = resultSet.getDouble(6);
                String subjects = resultSet.getString(7);
                String position = resultSet.getString(8);
                Position position1 = Position.valueOf(position);
                Teacher teacher = new Teacher(id, fullName, age, email, phoneNumber, salary, subjects, position1);
                teachers.add(teacher);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FileWrite.fileWriteFromService("allsubject.txt", teachers.toString());

        return teachers;
    }

    @Override
    public Integer updateFullNameAndSubject(Integer id, String fullname, String subject) {
        String query = "Update Teacher set full_name=?,subject=? where id=?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, fullname);
            stmt.setString(2, subject);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FileWrite.fileWriteFromService("nameandsubject.txt", id.toString());
        return id;
    }

    @Override
    public void updateSalary(Integer id, double salary) {
        String query = "Update Teacher salary=? where id=?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDouble(1, salary);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        FileWrite.fileWriteFromService("updatesalary.txt", id.toString());


    }
}
