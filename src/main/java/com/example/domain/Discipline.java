package com.example.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by jackson on 9/24/16.
 */
@Entity
@Table(name = "DISCIPLINES")
public class Discipline {
    private int id;
    private String name;
    private Collection<Attendance> attendances;
    private Collection<Grade> grades;
    private Collection<Teacher> teachers;
    private Collection<Group> groups;

    @GenericGenerator(name = "generator", strategy = "seqhilo", parameters = { @Parameter(name = "max_lo", value = "1"),
            @Parameter(name = "sequence", value = "DISCIPLINES_ID_SEQ") })
    @GeneratedValue(generator = "generator")
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "NAME", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discipline discipline = (Discipline) o;

        return id == discipline.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @OneToMany(mappedBy = "discipline")
    public Collection<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Collection<Attendance> attendances) {
        this.attendances = attendances;
    }

    @OneToMany(mappedBy = "discipline")
    public Collection<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
    }

    @ManyToMany
    @JoinTable(name = "DISCIPLINE_TEACHERS", joinColumns = @JoinColumn(name = "DISCIPLINE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID"))
    public Collection<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<Teacher> teachers) {
        this.teachers = teachers;
    }

    @ManyToMany(mappedBy = "disciplines")
    public Collection<Group> getGroups() {
        return groups;
    }

    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }
}
