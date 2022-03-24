package ru.job4j.dreamjob.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Candidate implements Serializable {

    private int id;
    private String name;
    private String desc;
    private LocalDate created;
    private boolean visible;
    private City city;

    public Candidate() {
    }

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
        this.created = LocalDate.now();
    }

    public Candidate(int id, String name, String desc, LocalDate created, City city) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.city = city;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id
                && visible == candidate.visible
                && Objects.equals(name, candidate.name)
                && Objects.equals(desc, candidate.desc)
                && Objects.equals(created, candidate.created)
                && Objects.equals(city, candidate.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, created, visible, city);
    }
}
