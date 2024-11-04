package org.example.model.enity;


import org.example.model.enums.Position;

public class Teacher extends User {
    private double salary;
    private String subject;
    private Position position;


    public Teacher(Integer id, String fullName, Integer age, String email, String phoneNumber, double salary, String subject, Position position) {
        super(id, fullName, age, email, phoneNumber);
        this.salary = salary;
        this.subject = subject;
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Teacher{" + super.toString() +
                "salary=" + salary +
                ", subject='" + subject + '\'' +
                ", position=" + position +
                "} " + "\n";
    }
}
