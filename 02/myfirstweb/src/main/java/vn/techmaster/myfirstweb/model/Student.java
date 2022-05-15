package vn.techmaster.myfirstweb.model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private LocalDate dob;

    @Override
    public String toString() {
        return "Student [dob=" + dob + ", id=" + id + ", name=" + name + "]";
    }

    public Student(int id, String name, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

}
