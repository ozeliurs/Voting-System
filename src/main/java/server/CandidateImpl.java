package server;

import shared.Candidate;

import java.io.Serializable;
import java.util.Objects;

public class CandidateImpl implements Candidate, Serializable {
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getPitch() {
        return pitch;
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

    @Override
    public String toString() {
        return "CandidateImpl{" + "id=" + id +
                ", name='" + name + "'" +
                ", surname='" + surname + "'" +
                ", pitch='" + pitch + "'" +
                "}";
    }
}
