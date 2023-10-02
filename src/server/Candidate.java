package server;

public class Candidate implements shared.Candidate {
    private int id;
    private String name;
    private String surname;
    //private Pitch pitch;

    public Candidate(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        //this.pitch = pitch;
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
}
