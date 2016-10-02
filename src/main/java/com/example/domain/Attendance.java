package com.example.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jackson on 9/24/16.
 */
@Entity
@Table(name = "ATTENDANCES")
public class Attendance {
    private int id;
    private Date date;
    private Student student;
    private Discipline discipline;

    @GenericGenerator(name = "generator", strategy = "seqhilo", parameters = { @Parameter(name = "max_lo", value = "1"),
            @Parameter(name = "sequence", value = "ATTENDANCES_ID_SEQ") })
    @GeneratedValue(generator = "generator")
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "DATE_", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendance attendance = (Attendance) o;

        return id == attendance.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "DISCIPLINE_ID", referencedColumnName = "ID", nullable = false)
    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
