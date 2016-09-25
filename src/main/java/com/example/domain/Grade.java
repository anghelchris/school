package com.example.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jackson on 9/24/16.
 */
@Entity
@Table(name = "GRADES", schema = "SCHOOL", catalog = "")
public class Grade {
    private int id;
    private int value;
    private Date date;
    private Discipline discipline;
    private Student student;

    @GenericGenerator(name = "generator", strategy = "seqhilo", parameters = { @Parameter(name = "max_lo", value = "1"),
            @Parameter(name = "sequence", value = "GRADES_ID_SEQ") })
    @GeneratedValue(generator = "generator")
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name = "VALUE", nullable = false, precision = 0)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    
    @Column(name = "DATE_", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Grade grade = (Grade) o;
//
//        if (id != grade.id) return false;
//        if (value != grade.value) return false;
//        if (date != null ? !date.equals(grade.date) : grade.date != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id;
//        result = 31 * result + value;
//        result = 31 * result + (date != null ? date.hashCode() : 0);
//        return result;
//    }

    @ManyToOne
    @JoinColumn(name = "DISCIPLINE_ID", referencedColumnName = "ID", nullable = false)
    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, value, date, discipline, student);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
//        final Grade other = (Grade) obj;
//        return Objects.equals(this.id, other.id)
//                && Objects.equals(this.value, other.value)
//                && Objects.equals(this.date, other.date)
//                && Objects.equals(this.discipline, other.discipline)
//                && Objects.equals(this.student, other.student);
//    }
}
