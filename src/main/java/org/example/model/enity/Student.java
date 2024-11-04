package org.example.model.enity;

import org.example.model.enums.CoursYear;

public class Student extends User {
    private String major;
    private double gpa;
    private CoursYear coursYear;

    public Student() {
    }

    public Student(Integer id, String fullName, Integer age, String email, String phoneNumber, String major, double gpa, CoursYear coursYear) {
        super(id, fullName, age, email, phoneNumber);
        this.major = major;
        this.gpa = gpa;
        this.coursYear = coursYear;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {

        this.major = major;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {

        this.gpa = gpa;
    }

    public CoursYear getCoursYear() {
        return coursYear;
    }

    public void setCoursYear(CoursYear coursYear) {
        this.coursYear = coursYear;
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() +
                "major='" + major + '\'' +
                ", gpa=" + gpa +
                ", coursYear=" + coursYear +
                "} " + "\n";
    }
}
