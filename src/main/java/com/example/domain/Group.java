package com.example.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by jackson on 9/24/16.
 */
@Entity
@Table(name = "GROUPS")
public class Group {
    private int id;
    private String name;
    private Collection<Student> students;
    private Collection<Discipline> disciplines;
    private GeneralGroup generalGroup;

    @GenericGenerator(name = "generator", strategy = "seqhilo", parameters = { @Parameter(name = "max_lo", value = "1"),
            @Parameter(name = "sequence", value = "GROUPS_ID_SEQ") })
    @GeneratedValue(generator = "generator")
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "NAME", nullable = false, length = 3)
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

        Group group = (Group) o;

        return id == group.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @OneToMany(mappedBy = "group")
    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @ManyToMany
    @JoinTable(name = "GROUP_DISCIPLINES",  joinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "DISCIPLINE_ID", referencedColumnName = "ID"))
    public Collection<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Collection<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @ManyToOne
    @JoinColumn(name = "GENERAL_GROUP_ID", referencedColumnName = "ID", nullable = false)
    public GeneralGroup getGeneralGroup() {
        return generalGroup;
    }

    public void setGeneralGroup(GeneralGroup generalGroup) {
        this.generalGroup = generalGroup;
    }
}
