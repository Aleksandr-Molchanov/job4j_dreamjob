package ru.job4j.dreamjob;

import java.util.Objects;

public class Candidate {

    private int id;

    private String name;

    private String resume;

    public Candidate(int id, String name, String resume) {
        this.id = id;
        this.name = name;
        this.resume = resume;
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
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
                && Objects.equals(name, candidate.name)
                && Objects.equals(resume, candidate.resume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, resume);
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", resume='" + resume + '\''
                + '}';
    }
}
