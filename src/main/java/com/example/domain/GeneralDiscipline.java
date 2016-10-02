package com.example.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "GENERAL_DISCIPLINES")
public class GeneralDiscipline {
    private int id;
    private String name;
    private Collection<Discipline> disciplines;
    private GeneralGroup generalGroup;

    @GenericGenerator(name = "generator", strategy = "seqhilo", parameters = { @org.hibernate.annotations.Parameter(name = "max_lo", value = "1"),
            @org.hibernate.annotations.Parameter(name = "sequence", value = "GENERAL_DISCIPLINES_ID_SEQ") })
    @GeneratedValue(generator = "generator")
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
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

        GeneralDiscipline that = (GeneralDiscipline) o;

        if (id != that.id) return false;
        if (name != null ? ! name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "generalDiscipline")
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