package server;

import java.util.Objects;

public class CandidateImpl implements shared.Candidate {
    private int id;
    private String name;
    private String surname;
    private String pitch;

    public CandidateImpl(int id, String name, String surname, String pitch) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pitch = pitch;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateImpl candidate = (CandidateImpl) o;
        return id == candidate.id && Objects.equals(name, candidate.name) && Objects.equals(surname, candidate.surname) && Objects.equals(pitch, candidate.pitch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, pitch);
    }
}
