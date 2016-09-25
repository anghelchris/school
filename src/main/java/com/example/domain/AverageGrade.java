package com.example.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Created by jackson on 9/24/16.
 */
@Entity
@Table(name = "AVERAGE_GRADES")
public class AverageGrade {
    private int id;
    private Double firstTrimester;
    private Double secondTrimester;
    private Double thirdTrimester;
    private Double finalGrade;
    private Integer year;
    private Student student;

    @GenericGenerator(name = "generator", strategy = "seqhilo", parameters = { @Parameter(name = "max_lo", value = "1"),
            @Parameter(name = "sequence", value = "AVERAGE_GRADES_ID_SEQ") })
    @GeneratedValue(generator = "generator")
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "FIRST_TRIMESTER", nullable = true, precision = 2)
    public Double getFirstTrimester() {
        return firstTrimester;
    }

    public void setFirstTrimester(Double firstTrimester) {
        this.firstTrimester = firstTrimester;
    }


    @Column(name = "SECOND_TRIMESTER", nullable = true, precision = 2)
    public Double getSecondTrimester() {
        return secondTrimester;
    }

    public void setSecondTrimester(Double secondTrimester) {
        this.secondTrimester = secondTrimester;
    }


    @Column(name = "THIRD_TRIMESTER", nullable = true, precision = 2)
    public Double getThirdTrimester() {
        return thirdTrimester;
    }

    public void setThirdTrimester(Double thirdTrimester) {
        this.thirdTrimester = thirdTrimester;
    }


    @Column(name = "FINAL_GRADE", nullable = true, precision = 2)
    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }


    @Column(name = "YEAR", nullable = true, precision = 0)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AverageGrade that = (AverageGrade) o;

        if (id != that.id) return false;
        if (firstTrimester != null ? !firstTrimester.equals(that.firstTrimester) : that.firstTrimester != null)
            return false;
        if (secondTrimester != null ? !secondTrimester.equals(that.secondTrimester) : that.secondTrimester != null)
            return false;
        if (thirdTrimester != null ? !thirdTrimester.equals(that.thirdTrimester) : that.thirdTrimester != null)
            return false;
        if (finalGrade != null ? !finalGrade.equals(that.finalGrade) : that.finalGrade != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstTrimester != null ? firstTrimester.hashCode() : 0);
        result = 31 * result + (secondTrimester != null ? secondTrimester.hashCode() : 0);
        result = 31 * result + (thirdTrimester != null ? thirdTrimester.hashCode() : 0);
        result = 31 * result + (finalGrade != null ? finalGrade.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
