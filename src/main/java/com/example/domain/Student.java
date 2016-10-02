package com.example.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by jackson on 9/24/16.
 */
@Entity
@Table(name = "STUDENTS")
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private Collection<Attendance> attendances;
    private Collection<AverageGrade> averageGrades;
    private Collection<Grade> grades;
    private Group group;

    @GenericGenerator(name = "generator", strategy = "seqhilo", parameters = { @Parameter(name = "max_lo", value = "1"),
            @Parameter(name = "sequence", value = "STUDENTS_ID_SEQ") })
    @GeneratedValue(generator = "generator")
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "FIRST_NAME", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Column(name = "LAST_NAME", nullable = true, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Column(name = "AGE", nullable = false, precision = 0)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id == student.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @OneToMany(mappedBy = "student")
    public Collection<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Collection<Attendance> attendances) {
        this.attendances = attendances;
    }

    @OneToMany(mappedBy = "student")
    public Collection<AverageGrade> getAverageGrades() {
        return averageGrades;
    }

    public void setAverageGrades(Collection<AverageGrade> averageGrades) {
        this.averageGrades = averageGrades;
    }

    @OneToMany(mappedBy = "student")
    public Collection<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
    }

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
